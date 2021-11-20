package cn.xavier.org.service;

import cn.xavier.basic.service.IBaseService;
import cn.xavier.org.domain.Shop;

/**
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
public interface IShopService extends IBaseService<Shop> {

    /**
     * 店铺入驻
     *
     * @param
     */
    void settleIn(Shop shop);
}
