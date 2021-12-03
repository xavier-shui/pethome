package cn.xavier.product.controller;

import cn.xavier.basic.constant.MarketStateConstants;
import cn.xavier.basic.util.AjaxResponse;
import cn.xavier.basic.util.LoginContext;
import cn.xavier.basic.util.PageList;
import cn.xavier.product.domain.Product;
import cn.xavier.product.query.ProductQuery;
import cn.xavier.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    /**
     * 前后台的分页列表
     *
     * @param productQuery product query
     * @return the page list
     */
    @PostMapping
    public PageList<Product> pageQuery(@RequestBody ProductQuery productQuery) {
        return productService.queryPage(productQuery);
    }

    /**
     * Batch on or off the market 批量上下架
     *
     * @param ids     ids
     * @param request request
     * @return the ajax response
     */
    @PostMapping({"/onsale", "/offsale"})
    public AjaxResponse batchOnOrOffTheMarket(@RequestBody List<Long> ids, HttpServletRequest request) {
        if (request.getRequestURI().contains("onsale")) {
            productService.batchOnOrOffTheMarket(ids, MarketStateConstants.ON_THE_MARKET);
        } else {
            productService.batchOnOrOffTheMarket(ids, MarketStateConstants.OFF_THE_MARKET);
        }
        return AjaxResponse.of();
    }

    /**
     * Query by id 用户查看详情页面
     *
     * @param id id
     * @return the product
     */
    @GetMapping("/{id}")
    public Product queryById(@PathVariable Long id) {
        return productService.findDetailById(id);
    }

    /**
     * 新增服务
     *
     * @param product product
     * @param request request
     * @return the ajax response
     */
    @PutMapping
    public AjaxResponse add(@RequestBody Product product, HttpServletRequest request) {
        productService.add(product, LoginContext.getLoginInfo(request));
        return AjaxResponse.of();
    }

    /**
     * Remove by id
     *
     * @param id id
     * @return the ajax response
     */
    @DeleteMapping("/{id}")
    public AjaxResponse removeById(@PathVariable Long id) {
        productService.remove(id);
        return AjaxResponse.of();
    }

    /**
     * 批量删除
     *
     * @param ids ids
     * @return the ajax response
     */
    @PatchMapping
    public AjaxResponse batchRemove(@RequestBody List<Long> ids) {
        productService.batchRemove(ids);
        return AjaxResponse.of();
    }

}
