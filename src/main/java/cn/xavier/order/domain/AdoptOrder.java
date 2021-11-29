package cn.xavier.order.domain;

import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AdoptOrder extends BaseDomain {
    private String digest;
    private Integer state;
    private BigDecimal price;
    private String orderSn;
    private String paySn;
    private Date lastPayTime;
    private Date lastConfirmTime;
    private Long pet_id;
    private Long user_id;
    private Long shop_id;

}
