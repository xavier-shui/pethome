package cn.xavier.order.service;

import cn.xavier.basic.domain.LoginInfo;
import cn.xavier.basic.service.IBaseService;
import cn.xavier.order.domain.AdoptOrder;

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
}
