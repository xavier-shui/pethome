package cn.xavier.basic.dto;

import lombok.Data;

/**
 * @author Zheng-Wei Shui
 * @date 11/25/2021
 */
@Data
public class BinderDto {
    private String phone;
    private String verifyCode;
    private String accessToken;
    private String openId;
}
