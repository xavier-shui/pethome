package cn.xavier.order.service;

import cn.xavier.basic.service.IBaseService;
import cn.xavier.order.domain.ProductOrder;
import cn.xavier.user.domain.User;

import java.util.Map;

public interface IProductOrderService extends IBaseService<ProductOrder> {
    String submit(Map<String, Object> params, User user);

    /**
     * 修改服务订单订单状态
     * 修改支付单状态
     * @param orderId
     */
    void cancelOrder(Long orderId);
}
