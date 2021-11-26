package cn.xavier.user.controller;

import cn.xavier.basic.util.AjaxResponse;
import cn.xavier.user.dto.UserDto;
import cn.xavier.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zheng-Wei Shui
 * @date 11/23/2021
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * Register by phone 手机号注册
     *
     * @param userDto user dto
     * @return the ajax response
     */
    @PostMapping("/registerByPhone")
    public AjaxResponse registerByPhone(@RequestBody UserDto userDto) {
        userService.registerByPhone(userDto);
        return AjaxResponse.of();
    }
}
