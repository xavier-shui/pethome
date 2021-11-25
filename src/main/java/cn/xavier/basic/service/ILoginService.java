package cn.xavier.basic.service;

import cn.xavier.basic.dto.BinderDto;
import cn.xavier.basic.dto.LoginDto;
import cn.xavier.basic.util.AjaxResponse;

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

    /**
     * Wechat 微信登录
     *
     * @param code code
     * @return the map
     */
    AjaxResponse wechat(String code);

    /**
     * Wechat binder 绑定微信账号至平台账号
     *
     * @param binderDto binder dto
     * @return the ajax response
     */
    AjaxResponse wechatBinder(BinderDto binderDto);
}
