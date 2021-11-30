package cn.xavier.order.domain;

import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 宠物收购订单
 */
@Data
public class PetAcquisitionOrder extends BaseDomain {
    private String digest;
    //0 待支付 1 待报账 2 待打款  3 完成
    private Integer state;
    private BigDecimal price;
	private String address;
    private String orderSn;
    private String paySn;
    private Long pet_id;
    private Long user_id;
    private Integer paytype;
    private Long shop_id;
    private Long emp_id;

}
