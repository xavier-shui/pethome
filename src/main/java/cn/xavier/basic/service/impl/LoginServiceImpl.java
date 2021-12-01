package cn.xavier.basic.service.impl;

import cn.xavier.basic.constant.LoginInfoConstants;
import cn.xavier.basic.constant.RedisConstants;
import cn.xavier.basic.constant.WechatConstants;
import cn.xavier.basic.domain.LoginInfo;
import cn.xavier.basic.dto.BinderDto;
import cn.xavier.basic.dto.LoginDto;
import cn.xavier.basic.exception.BusinessException;
import cn.xavier.basic.mapper.LoginInfoMapper;
import cn.xavier.basic.service.ILoginService;
import cn.xavier.basic.util.*;
import cn.xavier.user.domain.User;
import cn.xavier.user.domain.WxUser;
import cn.xavier.user.mapper.UserMapper;
import cn.xavier.user.mapper.WxUserMapper;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static cn.xavier.basic.constant.SmsCodeTypeConstants.BINDER;
import static cn.xavier.basic.util.TypeConverterUtils.toLoginInfo;

/**
 * @author Zheng-Wei Shui
 * @date 11/23/2021
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private LoginInfoMapper loginInfoMapper;

    // 声明泛型会导致注入失败
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private WxUserMapper WxUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WxUserMapper wxUserMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Map<String, String> account(LoginDto loginDto) {
        // 参数校验
        GuardClauseUtil.check(DtoUtil.anyEmptyFieldExist(loginDto), "必要参数不能为空!");

        // 从数据库查用户
        LoginInfo loginInfo = loginInfoMapper.loadByLoginDto(loginDto);
        GuardClauseUtil.check(loginInfo == null, "用户名或密码错误!");

        // 比对密码
        String md5PasswordProvided = MD5Utils.encrypByMd5(loginDto.getPassword() + loginInfo.getSalt());
        GuardClauseUtil.check(!md5PasswordProvided.equals(loginInfo.getPassword()), "用户名或密码错误!");


        // 如果登录成功就设置token，再加loginInfo返回前端(注意敏感参数置空)
        return getLoginInfoMap(loginInfo);
    }



    @Override
    public AjaxResponse wechat(String code) {
        // 参数校验
        if (!StringUtils.hasLength(code)) {
            throw new BusinessException("必要参数不能为空!");
        }
        // 根据code获取token和openID
        String tokenUrl = WechatConstants.getSpecifiedTokenUrl(code);
        // 成功响应{
        //     "access_token":"ACCESS_TOKEN",
        //         "expires_in":7200,
        //         "refresh_token":"REFRESH_TOKEN",
        //         "openid":"OPENID",
        //         "scope":"SCOPE",
        //         "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
        // }
        String tokenResult = HttpClientUtils.httpGet(tokenUrl);
        // code只能用一次，失败响应{"errcode":40029,"errmsg":"invalid code, rid: 619f1ad1-6177a56b-7d09fe9f"}
        JSONObject tokenResultJson = JSONObject.parseObject(tokenResult);
        if (tokenResultJson.get("errcode") != null) {
            return  AjaxResponse.of()
                    .setSuccess(false)
                    .setMessage("授权临时票据已超时10分钟或已经使用过!");
        }

        // 查询openID，判断是否绑定过
        String openId = tokenResultJson.getString("openid");
        WxUser wxUserInDb = WxUserMapper.loadByOpenId(openId);
        if (wxUserInDb != null && wxUserInDb.getUser_id() != null) { // 绑过直接登录
            return AjaxResponse.of().setResultObj(
                    getLoginInfoMap(loginInfoMapper.loadByUserId(wxUserInDb.getUser_id())));
        }

        // 让用户绑手机号(token和openid返回给前端，稍后随手机号一起传来后端，保存用户详细信息)
        String accessToken = tokenResultJson.getString("access_token");
        return AjaxResponse.of()
                .setSuccess(false)
                .setMessage("未绑定")
                .setResultObj(String.format("access_token=%s&openid=%s", accessToken, openId));
    }

    @Override
    @Transactional
    public AjaxResponse wechatBinder(BinderDto binderDto) {
        // 参数校验
        if (DtoUtil.anyEmptyFieldExist(binderDto)) {
            throw new BusinessException("必要参数不能为空!");
        }

        // 验证码校验
        redisUtil.verifyCodeCheck(RedisConstants.getSmsCodeKeyPrefix(BINDER) + binderDto.getPhone(),
                binderDto.getVerifyCode());

        // 获取用户在微信平台的个人信息, 用来生成t_wxuser表
        String userInfoUrl = WechatConstants.getSpecifiedUserInfoUrl(binderDto.getAccessToken(), binderDto.getOpenId());
        String userInfoResult = HttpClientUtils.httpGet(userInfoUrl);
        // JSON字符串直接映射到指定类的对象
        WxUser wxUser = JSONObject.parseObject(userInfoResult, WxUser.class);
        User user = userMapper.loadByPhone(binderDto.getPhone());
        LoginInfo loginInfo;
        // 新用户，生成t_user和t_logininfo表数据
        if (user == null) {
            user = initUser(binderDto.getPhone(), wxUser);
            loginInfo = toLoginInfo(user);
            loginInfoMapper.save(loginInfo);
            user.setLogininfo_id(loginInfo.getId());
            userMapper.save(user);

        } else {
            // 可以不联表查，就不用loadByUserId了提高效率
            loginInfo = loginInfoMapper.loadById(user.getLogininfo_id());
            // 就用以前的头像，不更新微信头像了
        }
        // 用户如果已在本平台注册过，直接绑定
        wxUser.setUser_id(user.getId());
        wxUserMapper.save(wxUser);
        // 登录信息返回
        return AjaxResponse.of().setResultObj(getLoginInfoMap(loginInfo));
    }


    /**
     * 通过前端传递的一个参数phone初始化用户 , WxUser用来获取头像
     * @param phone
     * @param wxUser
     * @return
     */
    private User initUser(String phone, WxUser wxUser) {
        User user = new User();
        user.setUsername(phone);
        user.setPhone(phone);
        String salt = StrUtils.getComplexRandomString(32);
        // 初始密码随机生成， 发短信告诉用户
        String password = StrUtils.getRandomString(5);
        System.out.println("尊敬的用户：您好，您在本站的初始密码为" + password + "。您也可选择手机号+密码登录本站!");
        String md5Password = MD5Utils.encrypByMd5(password + salt);
        user.setSalt(salt);
        user.setPassword(md5Password);
        // 头像
        user.setHeadImg(wxUser.getHeadimgurl());
        return user;
    }

    /**
     * 保存登录信息至redis并返回用户token和loginInfo的Map
     * @param loginInfo
     * @return
     */
    private Map<String, String> getLoginInfoMap(LoginInfo loginInfo) {
        if (loginInfo.getDisable() == LoginInfoConstants.DISABLED) {
            throw new BusinessException("您的账号已被冻结!");
        }

        Map<String, String> map = new HashMap<>();
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token, loginInfo, 30, TimeUnit.MINUTES);
        map.put("token", token);
        // 敏感参数置空
        loginInfo.setSalt(null);
        loginInfo.setPassword(null);
        map.put("loginInfo", DtoUtil.toJsonString(loginInfo));
        return map;
    }
}
