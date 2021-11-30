package cn.xavier.order.domain;

import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductOrder extends BaseDomain {
    private String digest;
    private Integer state;
    private BigDecimal price;
    private String orderSn;
    private String paySn;
    private Date lastPayTime;
    private Date lastConfirmTime;
    private Long product_id;
    private Long user_id;
    private Long shop_id;  //分配
    private Long address_id;

}
