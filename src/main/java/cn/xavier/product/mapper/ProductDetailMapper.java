package cn.xavier.product.mapper;

import cn.xavier.basic.mapper.BaseMapper;
import cn.xavier.product.domain.ProductDetail;

import java.io.Serializable;

public interface ProductDetailMapper extends BaseMapper<ProductDetail> {

    ProductDetail loadByProductId(Long productId);

    void removeByProductId(Serializable id);

    void updateByProductId(ProductDetail detail);
}
