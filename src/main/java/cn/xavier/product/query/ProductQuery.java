package cn.xavier.product.query;

import cn.xavier.basic.query.BaseQuery;
import lombok.Data;

@Data
public class ProductQuery extends BaseQuery {
    private Integer state;
}
