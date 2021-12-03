package cn.xavier.product.domain;

import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;

@Data
public class ProductDetail extends BaseDomain {
    private Long product_id;
    private String orderNotice;
    private String intro;
}
