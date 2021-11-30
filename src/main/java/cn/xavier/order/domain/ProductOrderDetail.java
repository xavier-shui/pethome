package cn.xavier.order.domain;

import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductOrderDetail extends BaseDomain {
    private String name; //名称
    private String resources;//资源路径
    private BigDecimal saleprice;//售价
    private BigDecimal costprice;//成本价
    private Date offsaletime;//下架时间
    private Date onsaletime;//上架时间
    private Integer state = 0;// 状态 0 下架 1 上架
    private Date createtime; //创建时间
    private Long salecount; // 销量
    private Long order_id;


}
