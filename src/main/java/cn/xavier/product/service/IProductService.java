package cn.xavier.product.service;

import cn.xavier.basic.domain.LoginInfo;
import cn.xavier.basic.service.IBaseService;
import cn.xavier.product.domain.Product;

import java.util.List;

public interface IProductService extends IBaseService<Product> {

    /**
     * 服务批量上下架
     *  @param ids              ids
     * @param state on or off the market
     */
    void batchOnOrOffTheMarket(List<Long> ids, int state);

    /**
     * Find detail by id 服务详情页面
     *
     * @param id id
     * @return the product
     */
    Product findDetailById(Long id);

    /**
     * 添加服务
     *
     * @param product   product
     * @param loginInfo login info
     */
    void add(Product product, LoginInfo loginInfo);
}
