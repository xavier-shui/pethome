package cn.xavier.order.domain;

import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;

import java.util.Date;

@Data
public class OrderAddress extends BaseDomain {
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
    private Long order_id;
    private String orderSn;

}
