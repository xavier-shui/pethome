package cn.xavier.user.service;

import cn.xavier.basic.service.IBaseService;
import cn.xavier.user.domain.User;
import cn.xavier.user.dto.UserDto;

/**
 * 业务层
 * @author Zheng-Wei Shui
 * @date 11/22/2021
 */
public interface IUserService extends IBaseService<User> {
    void registerByPhone(UserDto userDto);
}
