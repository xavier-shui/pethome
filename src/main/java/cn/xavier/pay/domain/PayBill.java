package cn.xavier.pay.domain;

import cn.xavier.basic.domain.BaseDomain;
import cn.xavier.pay.constants.PayConstants;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class PayBill extends BaseDomain {
    private String digest;
    private BigDecimal money;
    private String unionPaySn;
    private Integer state = PayConstants.TO_BE_PAID;
    private LocalDateTime lastPayTime;
    private Long payChannel; //0 余额 1 支付宝 2 微信 3 银联
    private String businessType;
    private Long businessKey;
    private Date updateTime;
    private Date createTime = new Date();
    private Long user_id;
    private Long shop_id;
    private String nickName;

}
