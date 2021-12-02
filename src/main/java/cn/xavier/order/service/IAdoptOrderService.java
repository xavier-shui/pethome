package cn.xavier.order.service;

import cn.xavier.basic.domain.LoginInfo;
import cn.xavier.basic.service.IBaseService;
import cn.xavier.basic.util.PageList;
import cn.xavier.order.domain.AdoptOrder;
import cn.xavier.order.query.AdoptOrderQuery;

import java.util.Map;

public interface IAdoptOrderService extends IBaseService<AdoptOrder> {
    /**
     * Submit 客户领养订单
     *
     * @param params    params
     * @param loginInfo login info
     * @return the string
     */
    String submit(Map<String, Object> params, LoginInfo loginInfo);

    /**
     * Query page page list
     *
     * @param adoptOrderQuery adopt order query
     * @param loginInfo       可以判断前后台
     * @return the page list
     */
    PageList<AdoptOrder> queryPage(AdoptOrderQuery adoptOrderQuery, LoginInfo loginInfo);

    /**
     * Find detail， pet, shop, user
     *
     * @param id id
     * @return the adopt order
     */
    AdoptOrder findDetailById(Long id);

    /**
     * Quartz通过统一支付号取消订单
     *
     * @param paySn pay sn
     */
    void cancelByQuartz(String paySn);
}
