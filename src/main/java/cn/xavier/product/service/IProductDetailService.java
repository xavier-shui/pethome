package cn.xavier.product.service;

import cn.xavier.basic.service.IBaseService;
import cn.xavier.product.domain.ProductDetail;

public interface IProductDetailService extends IBaseService<ProductDetail> {
    ProductDetail getByProductId(Long productId);
}
