package cn.xavier.order.mapper;

import cn.xavier.basic.mapper.BaseMapper;
import cn.xavier.order.domain.AdoptOrder;
import cn.xavier.order.domain.ProductOrder;

public interface ProductOrderMapper extends BaseMapper<ProductOrder> {

    ProductOrder loadByPaySn(String paySn);

    /**
     * Load detail by id
     *
     * @param id id
     * @return the product order
     */
    ProductOrder loadDetailById(Long id);
}
