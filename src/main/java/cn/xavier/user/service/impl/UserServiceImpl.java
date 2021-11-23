package cn.xavier.user.service.impl;

import cn.xavier.basic.constant.LoginInfoConstants;
import cn.xavier.basic.exception.BusinessException;
import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.basic.util.DtoUtil;
import cn.xavier.basic.util.MD5Utils;
import cn.xavier.basic.util.StrUtils;
import cn.xavier.basic.domain.LoginInfo;
import cn.xavier.user.domain.User;
import cn.xavier.user.dto.UserDto;
import cn.xavier.basic.mapper.LoginInfoMapper;
import cn.xavier.user.mapper.UserMapper;
import cn.xavier.user.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        // 两次密码一致性校验
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new BusinessException("两次输入的密码不一致!");
        }

        // 生成User对象, 它的字段多，先生成，方便复制到LoginInfo
        User user = userDto2User(userDto);
        // 生成LoginInfo对象
        LoginInfo loginInfo = user2LoginInfo(user);

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

    private LoginInfo user2LoginInfo(User user) {
        LoginInfo loginInfo = new LoginInfo();
        BeanUtils.copyProperties(user, loginInfo);
        // 设置类型
        loginInfo.setType(LoginInfoConstants.USER);
        return loginInfo;
    }

    // TODO update 和 remove 需要关联修改t_logininfo表
    @Override
    public void update(User user) {
        super.update(user);
    }

    @Override
    public void remove(Long id) {
        super.remove(id);
    }
}
