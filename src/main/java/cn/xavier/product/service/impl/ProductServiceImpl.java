package cn.xavier.product.service.impl;

import cn.xavier.basic.constant.MarketStateConstants;
import cn.xavier.basic.domain.LoginInfo;
import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.org.mapper.EmployeeMapper;
import cn.xavier.product.domain.Product;
import cn.xavier.product.domain.ProductDetail;
import cn.xavier.product.mapper.ProductDetailMapper;
import cn.xavier.product.mapper.ProductMapper;
import cn.xavier.product.service.IProductService;
import cn.xavier.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl extends BaseServiceImpl<Product> implements IProductService {

    @Autowired
    private ProductDetailMapper productDetailMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    @Transactional
    public void batchOnOrOffTheMarket(List<Long> ids, int state) {
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        params.put("state", state);
        params.put("date", LocalDateTime.now());
        // 多个参数可以用map或@Param
        productMapper.batchOnOrOffTheMarket(params);
    }

    @Override
    public Product findDetailById(Long id) {
        return productMapper.loadDetailById(id);
    }

    @Override
    public void add(Product product, LoginInfo loginInfo) {
        product.setShop_id(employeeMapper.loadByLoginInfoId(loginInfo.getId()).getShop_id());
        // 先保存获取自增ID
        productMapper.save(product);
        // 详情
        ProductDetail productDetail = product.getDetail();
        // 关联好再保存
        // 前端不填详情，detail是前端设置的默认的空字符串。 如果前端会传null，这里要判断
        productDetail.setProduct_id(product.getId());
        productDetailMapper.save(productDetail);
    }
}
