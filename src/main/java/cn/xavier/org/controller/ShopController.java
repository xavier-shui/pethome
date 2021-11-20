package cn.xavier.org.controller;

import cn.xavier.basic.util.AjaxResponse;
import cn.xavier.basic.util.PageList;
import cn.xavier.org.domain.Shop;
import cn.xavier.org.query.ShopQuery;
import cn.xavier.org.service.IShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 店铺的RestFul接口
 *
 * @author Zheng -Wei Shui
 * @date 11 /12/2021
 */
@RestController
@RequestMapping("/shop")
@Api(tags = "店铺接口",description = "店铺接口详细描述")
public class ShopController {

    @Autowired
    private IShopService shopService;

    @PostMapping("/settlement")
    public AjaxResponse settleIn(@RequestBody Shop shop) {
        shopService.settleIn(shop);
        return AjaxResponse.of();
    }

    /**
     * 新增或修改店铺
     *
     * @return the ajax response
     */
    @PutMapping
    @ApiOperation(value = "店铺添加或修改",notes = "如果有id就是修改否则就是添加")
    public AjaxResponse addOrUpdate(@RequestBody Shop shop) {
        if (shop.getId() == null) {
            shopService.add(shop);
        } else {
            shopService.update(shop);
        }
        return AjaxResponse.of();
    }

    /**
     * Remove by id , {}内为占位符
     * 一个参数，pathvariable可以不写value
     * @return the ajax response
     */
    @DeleteMapping("/{id}")
    public AjaxResponse removeById(@PathVariable Long id) {
        shopService.remove(id);
        return AjaxResponse.of();
    }

    /**
     * 查一个
     *
     * @param id id
     * @return the shop
     */
    @GetMapping("/{id}")
    public Shop queryById(@PathVariable Long id) {
        return shopService.findById(id);
    }

    /**
     * 查全部
     *
     * @return the shop
     */
    @GetMapping
    public List<Shop> queryAll() {
        return shopService.findAll();
    }

    /**
     * 分页 + 高级查询
     *
     * @param shopQuery shop query
     * @return the page list
     */
    @PostMapping("/list")
    public PageList<Shop> queryPage(@RequestBody ShopQuery shopQuery) {
         return shopService.queryPage(shopQuery);
    }

    /**
     * 批量删除店铺
     *
     * @param ids ids
     * @return the ajax response
     */
    @PatchMapping
    public AjaxResponse batchRemove(@RequestBody List<Long> ids) {
        shopService.batchRemove(ids);
        return AjaxResponse.of();
    }


}
