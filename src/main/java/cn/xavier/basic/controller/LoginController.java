package cn.xavier.basic.controller;

import cn.xavier.basic.dto.BinderDto;
import cn.xavier.basic.dto.LoginDto;
import cn.xavier.basic.service.ILoginService;
import cn.xavier.basic.util.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Zheng-Wei Shui
 * @date 11/23/2021
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ILoginService loginService;

    /**
     * 账号密码登录
     *
     * @param loginDto login dto
     * @return the ajax response
     */
    @PostMapping("/account")
    public AjaxResponse account(@RequestBody LoginDto loginDto) {
        Map<String, String> map = loginService.account(loginDto);
        return AjaxResponse.of().setResultObj(map);
    }

    @PostMapping("/wechat")
    public AjaxResponse wechat(@RequestBody Map<String, String> params) {
        String code = params.get("code"); // 用户同意授权，第三方应用得到临时票据
        // 防止csrf攻击(also know as XSRF, Cross Site Request Forgery)
        // 前端可设置为简单的随机数，后端这里检验略过未写
        String state = params.get("state");
        return loginService.wechat(code);
    }

    /**
     * Wechat binding 绑定微信账号至pethome平台
     *
     * @param binderDto 绑定相关参数
     * @return the ajax response
     */
    @PostMapping("/binder/wechat")
    public AjaxResponse wechatBinder(@RequestBody BinderDto binderDto) {
        return loginService.wechatBinder(binderDto);
    }
}
