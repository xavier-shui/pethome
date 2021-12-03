package cn.xavier.order.query;

import cn.xavier.basic.query.BaseQuery;
import lombok.Data;

@Data
public class ProductOrderQuery extends BaseQuery {
    private Long shop_id;
    private Long user_id;
}
