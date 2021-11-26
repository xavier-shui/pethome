package cn.xavier.user.service.impl;

import cn.xavier.basic.constant.LoginInfoConstants;
import cn.xavier.basic.constant.RedisConstants;
import cn.xavier.basic.domain.LoginInfo;
import cn.xavier.basic.exception.BusinessException;
import cn.xavier.basic.mapper.LoginInfoMapper;
import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.basic.util.DtoUtil;
import cn.xavier.basic.util.MD5Utils;
import cn.xavier.basic.util.RedisUtil;
import cn.xavier.basic.util.StrUtils;
import cn.xavier.user.domain.User;
import cn.xavier.user.dto.UserDto;
import cn.xavier.user.mapper.UserMapper;
import cn.xavier.user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static cn.xavier.basic.constant.SmsCodeTypeConstants.REGISTER;
import static cn.xavier.basic.util.TypeConverterUtils.toLoginInfo;

/**
 * @author Zheng-Wei Shui
 * @date 11/22/2021
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginInfoMapper loginInfoMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public void registerByPhone(UserDto userDto) {
        // 必要参数校验
        if (DtoUtil.anyEmptyFieldExist(userDto)) {
            throw new BusinessException("必要参数不能为空!");
        }



        // 是否已经注册校验
        User userinDb = userMapper.loadByPhone(userDto.getPhone());
        if (userinDb != null) {
            throw new BusinessException("该手机号已经注册过!");
        }

        // 验证码校验
        redisUtil.verifyCodeCheck(RedisConstants.getSmsCodeKeyPrefix(REGISTER) + userDto.getPhone(),
                userDto.getVerifyCode());


        // 两次密码一致性校验
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new BusinessException("两次输入的密码不一致!");
        }

        // 生成User对象, 它的字段多，先生成，方便复制到LoginInfo
        User user = userDto2User(userDto);
        // 生成LoginInfo对象
        LoginInfo loginInfo = toLoginInfo(user);

        // 保存到t_logininfo表, ID有用需返回
        loginInfoMapper.save(loginInfo);
        user.setLogininfo_id(loginInfo.getId());
        // 保存到t_user表
        userMapper.save(user);
    }


    private User userDto2User(UserDto userDto) {
        User user = new User();
        // 只有phone和password字段对应
        BeanUtils.copyProperties(userDto, user);

        user.setUsername(user.getPhone());

        // 密码加密
        String salt = StrUtils.getComplexRandomString(32);
        String md5Password = MD5Utils.encrypByMd5(user.getPassword() + salt);
        user.setSalt(salt);
        user.setPassword(md5Password);

        return user;
    }


    // update 和 remove 需要关联修改t_logininfo表
    @Override
    public void update(User user) {
        loginInfoMapper.update(toLoginInfo(user));
        super.update(user);
    }

    @Override
    public void remove(Long id) {
        loginInfoMapper.delete(userMapper.loadById(id).getLogininfo_id());
        super.remove(id);
    }
}
