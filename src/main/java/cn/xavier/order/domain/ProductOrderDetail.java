package cn.xavier.order.domain;

import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductOrderDetail extends BaseDomain {
    private String name; //名称
    private String resources;//资源路径
    private BigDecimal saleprice;//单价
    private BigDecimal costprice;//成本价
    private LocalDateTime offsaletime;//下架时间
    private LocalDateTime onsaletime;//上架时间
    private Integer state = 0;// 状态 0 下架 1 上架
    private LocalDateTime createtime; //创建时间
    private Long salecount; // 本次订单销量
    private Long order_id;
    private Long product_id;


}
