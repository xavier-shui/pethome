package cn.xavier.basic.controller;

import cn.xavier.basic.service.IVerifyCodeService;
import cn.xavier.basic.util.AjaxResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Zheng-Wei Shui
 * @date 11/22/2021
 */
@RestController
@RequestMapping("/verifyCode")
public class VerifyCodeController {

    @Autowired
    private IVerifyCodeService verifyCodeService;

    @PostMapping("/sendSmsCode")
    public AjaxResponse sendSmsCode(@RequestBody Map<String, String> parameters) { // Map代替对象接收参数
        String phone = parameters.get("phone");
        verifyCodeService.sendSmsCode(phone);
        return AjaxResponse.of();
    }
}
