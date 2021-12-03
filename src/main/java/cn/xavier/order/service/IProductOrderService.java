package cn.xavier.order.service;

import cn.xavier.basic.domain.LoginInfo;
import cn.xavier.basic.service.IBaseService;
import cn.xavier.basic.util.PageList;
import cn.xavier.order.domain.ProductOrder;
import cn.xavier.order.query.ProductOrderQuery;

import java.util.Map;

public interface IProductOrderService extends IBaseService<ProductOrder> {

    /**
     * 订单提交
     *
     * @param params    params
     * @param loginInfo login info
     * @return the string
     */
    String submit(Map<String, Object> params, LoginInfo loginInfo);

    /**
     * 修改服务订单订单状态
     * 修改支付单状态
     * @param orderId
     */
    void cancelOrder(Long orderId);

    /**
     * Cancel by quartz *
     *
     * @param paySn pay sn
     */
    void cancelByQuartz(String paySn);

    /**
     * Query page page list
     *
     * @param productOrderQuery product order query
     * @param loginInfo         login info
     * @return the page list
     */
    PageList<ProductOrder> queryPage(ProductOrderQuery productOrderQuery, LoginInfo loginInfo);

    /**
     * Find detail by id
     *
     * @param id id
     * @return the product order
     */
    ProductOrder findDetailById(Long id);
}
