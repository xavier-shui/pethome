package cn.xavier.product.mapper;

import cn.xavier.basic.mapper.BaseMapper;
import cn.xavier.product.domain.Product;

import java.util.Map;

public interface ProductMapper extends BaseMapper<Product> {

    Product loadDetailById(Long id);

    /**
     * Batch on or off the market
     *
     * @param params params
     */
    void batchOnOrOffTheMarket(Map<String, Object> params);
}
