package cn.xavier.pet.controller;

import cn.xavier.basic.util.AjaxResponse;
import cn.xavier.basic.util.PageList;
import cn.xavier.basic.constant.MarketStateConstants;
import cn.xavier.pet.domain.Pet;
import cn.xavier.pet.query.PetQuery;
import cn.xavier.pet.service.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private IPetService petService;

    /**
     * 前后台的分页列表
     *
     * @param petQuery pet query
     * @return the page list
     */
    @PostMapping
    public PageList<Pet> pageQuery(@RequestBody PetQuery petQuery) {
        return petService.queryPage(petQuery);
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
            petService.batchOnOrOffTheMarket(ids, MarketStateConstants.ON_THE_MARKET);
        } else {
            petService.batchOnOrOffTheMarket(ids, MarketStateConstants.OFF_THE_MARKET);
        }
        return AjaxResponse.of();
    }

    /**
     * Query by id 用户查看详情页面
     *
     * @param id id
     * @return the pet
     */
    @GetMapping("/{id}")
    public Pet queryById(@PathVariable Long id) {
        return petService.findDetailById(id);
    }

    /**
     * Adopt pet 直接领养，没有订单
     *
     * @param id id
     * @return the pet
     */
    // @GetMapping("/adopt/{id}")
    // public AjaxResponse adopt(@PathVariable Long id, HttpServletRequest request) {
    //     petService.adopt(id, LoginContext.getLoginInfo(request));
    //     return AjaxResponse.of();
    // }


}
