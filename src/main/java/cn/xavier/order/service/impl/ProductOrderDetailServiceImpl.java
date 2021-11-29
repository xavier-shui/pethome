package cn.xavier.order.service.impl;

import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.order.domain.ProductOrder;
import cn.xavier.order.domain.ProductOrderDetail;
import cn.xavier.order.service.IProductOrderDetailService;
import cn.xavier.order.service.IProductOrderService;
import org.springframework.stereotype.Service;

@Service
public class ProductOrderDetailServiceImpl extends BaseServiceImpl<ProductOrderDetail> implements IProductOrderDetailService {
}
