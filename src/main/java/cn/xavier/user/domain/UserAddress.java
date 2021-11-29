package cn.xavier.user.domain;

import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;

import java.util.Date;

/**
 * 用户地址
 */
@Data
public class UserAddress extends BaseDomain {
    private Date createTime;
    private Date updateTime;
    private String contacts;
    private String areaCode;
    private String address;
    private String fullAddress;
    private String phone;
    private String phoneBack;
    private String tel;
    private String postCode;
    private String email;

}
