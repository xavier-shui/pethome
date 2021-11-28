package cn.xavier.user.mapper;

import cn.xavier.basic.mapper.BaseMapper;
import cn.xavier.user.domain.User;

/**
 * 持久化层
 * @author Zheng-Wei Shui
 * @date 11/22/2021
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * Load by phone 根据手机号查询用户
     *
     * @param phone phone
     * @return the user
     */
    User loadByPhone(String phone);

    /**
     * Load by logininfo id
     *
     * @param loginInfoid login infoid
     * @return the user
     */
    User loadByLoginInfoId(Long loginInfoid);
}
