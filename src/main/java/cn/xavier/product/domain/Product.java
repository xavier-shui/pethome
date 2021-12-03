package cn.xavier.product.domain;

import cn.xavier.basic.domain.BaseDomain;
import cn.xavier.org.domain.Shop;
import cn.xavier.basic.constant.MarketStateConstants;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Product extends BaseDomain {
    private String name;
    private String resources;
    private BigDecimal saleprice;
    private LocalDateTime offsaletime;
    private LocalDateTime onsaletime;
    private Integer state = MarketStateConstants.OFF_THE_MARKET;
    private BigDecimal costprice;
    private LocalDateTime createtime = LocalDateTime.now();
    private Long salecount = 0L;
    private Long shop_id;
    private Shop shop;

    private Integer payType;

    private ProductDetail detail = new ProductDetail();

}
