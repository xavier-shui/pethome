package cn.xavier.user.dto;

import lombok.Data;

/**
 * @author Zheng-Wei Shui
 * @date 11/23/2021
 */
@Data
public class UserDto {
    private String phone;
    private String verifyCode;
    private String password;
    private String confirmPassword;
}
