package cn.xavier.org.domain;

import cn.xavier.basic.constant.StateConstants;
import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;

import java.util.Date;

/**
 * @author Zheng-Wei Shui
 * @date 11/20/2021
 */
@Data
public class Shop extends BaseDomain {
    private String name;
    private String tel;
    private Date registerTime = new Date();
    // 状态
    private Integer state = StateConstants.VERIFIED;
    private String address;
    private String logo;
    private Long admin_id;
    // 店长
    private Employee admin;
}
