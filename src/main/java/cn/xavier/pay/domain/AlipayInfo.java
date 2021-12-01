package cn.xavier.pay.domain;

import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;

@Data
public class AlipayInfo extends BaseDomain {
    private String merchant_private_key;
    private String appid;
    private String alipay_public_key;
    private Long shop_id;
    private String shopName;

}
