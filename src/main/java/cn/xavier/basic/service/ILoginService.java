package cn.xavier.basic.service;

import cn.xavier.basic.dto.LoginDto;

import java.util.Map;

/**
 * @author Zheng-Wei Shui
 * @date 11/23/2021
 */
public interface ILoginService {
    /**
     * Account map
     *
     * @param loginDto login dto
     * @return the map 前端要存到Storage
     */
    Map<String, String> account(LoginDto loginDto);
}
