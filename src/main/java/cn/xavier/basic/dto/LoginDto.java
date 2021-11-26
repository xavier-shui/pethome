package cn.xavier.basic.dto;

import cn.xavier.basic.constant.LoginInfoConstants;
import lombok.Data;

/**
 * @author Zheng-Wei Shui
 * @date 11/23/2021
 */
@Data
public class LoginDto {
    private String username;
    private String password;
    private Integer type = LoginInfoConstants.USER;
}
