package cn.xavier.product.service.impl;

import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.product.domain.ProductDetail;
import cn.xavier.product.mapper.ProductDetailMapper;
import cn.xavier.product.service.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductDetailServiceImpl extends BaseServiceImpl<ProductDetail> implements IProductDetailService {
    @Autowired
    private ProductDetailMapper productDetailMapper;

    @Override
    public ProductDetail getByProductId(Long productId) {
        return productDetailMapper.loadByProductId(productId);
    }
}
