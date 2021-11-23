package cn.xavier.basic.service.impl;

import cn.xavier.basic.constant.LoginInfoConstants;
import cn.xavier.basic.domain.LoginInfo;
import cn.xavier.basic.dto.LoginDto;
import cn.xavier.basic.exception.BusinessException;
import cn.xavier.basic.mapper.LoginInfoMapper;
import cn.xavier.basic.service.ILoginService;
import cn.xavier.basic.util.DtoUtil;
import cn.xavier.basic.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author Zheng-Wei Shui
 * @date 11/23/2021
 */
@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private LoginInfoMapper loginInfoMapper;

    // 声明泛型会导致注入失败
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Map<String, String> account(LoginDto loginDto) {
        // 参数校验
        if (DtoUtil.anyEmptyFieldExist(loginDto)) {
            throw new BusinessException("必要参数不能为空!");
        }

        // 从数据库查用户
        LoginInfo loginInfo = loginInfoMapper.loadByLoginDto(loginDto);
        if (loginInfo == null) {
            throw new BusinessException("用户名或密码错误!");
        }
        if (loginInfo.getDisable() == LoginInfoConstants.DISABLED) {
            throw new BusinessException("您的账号已被冻结!");
        }

        // 比对密码
        String md5PasswordProvided = MD5Utils.encrypByMd5(loginDto.getPassword() + loginInfo.getSalt());
        if (!md5PasswordProvided.equals(loginInfo.getPassword())) {
            throw new BusinessException("用户名或密码错误!");
        }

        // 登录成功设置token，再加loginInfo返回前端(注意敏感参数置空)
        Map<String, String> map = new HashMap<>();
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token, loginInfo, 30, TimeUnit.MINUTES);
        map.put("token", token);
        loginInfo.setSalt(null);
        loginInfo.setPassword(null);
        map.put("loginInfo", DtoUtil.toJsonString(loginInfo));
        return map;
    }
}
