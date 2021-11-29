package cn.xavier.order.service.impl;

import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.order.domain.ProductOrder;
import cn.xavier.order.mapper.ProductOrderMapper;
import cn.xavier.order.service.IProductOrderService;
import cn.xavier.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProductOrderServiceImpl extends BaseServiceImpl<ProductOrder> implements IProductOrderService {

    @Autowired
    private ProductOrderMapper productOrderMapper;


    @Override
    public String submit(Map<String, Object> params, User user) {
        return null;
    }

    @Override
    public void cancelOrder(Long orderId) {

    }
}
