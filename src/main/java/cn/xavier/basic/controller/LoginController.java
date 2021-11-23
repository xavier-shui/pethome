package cn.xavier.basic.controller;

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
}
