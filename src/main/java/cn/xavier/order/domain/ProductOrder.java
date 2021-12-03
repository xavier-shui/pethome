package cn.xavier.order.domain;

import cn.xavier.basic.domain.BaseDomain;
import cn.xavier.org.domain.Shop;
import cn.xavier.product.domain.Product;
import cn.xavier.user.domain.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProductOrder extends BaseDomain {
    private String digest;
    private Integer state;
    private BigDecimal price;
    private String orderSn;
    private String paySn;
    private LocalDateTime lastPayTime;
    private LocalDateTime lastConfirmTime;
    private Long product_id;
    private Product product;
    private Long user_id;
    private User user;
    private Long shop_id;  //分配
    private Shop shop;
    private Long address_id;

}
