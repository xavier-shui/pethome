package cn.xavier.basic.mapper;

import cn.xavier.basic.dto.LoginDto;
import cn.xavier.basic.domain.LoginInfo;

/**
 * 持久化层
 * @author Zheng-Wei Shui
 * @date 11/22/2021
 */
public interface LoginInfoMapper extends BaseMapper<LoginInfo> {

    /**
     * Load by login dto 用户名 邮箱 手机 之一匹配就行
     *
     * @param loginDto login dto
     * @return the login info
     */
    LoginInfo loadByLoginDto(LoginDto loginDto);

    /**
     * Load by user id 更据用户id联表查loginInfo
     *
     * @param userId user id
     * @return the login info
     */
    LoginInfo loadByUserId(Long userId);
}
