package cn.xavier.order.controller;

import cn.xavier.basic.util.AjaxResponse;
import cn.xavier.basic.util.LoginContext;
import cn.xavier.basic.util.PageList;
import cn.xavier.order.domain.ProductOrder;
import cn.xavier.order.query.ProductOrderQuery;
import cn.xavier.order.service.IProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/productOrder")
public class ProductOrderController {

    @Autowired
    private IProductOrderService productOrderService;


    /**
     * Submit 客户服务订单提交
     *
     * @param params  params
     * @param request request
     * @return the ajax response
     */
    @PostMapping("/submit")
    public AjaxResponse submit(@RequestBody Map<String,Object> params, HttpServletRequest request){
        String payData = productOrderService.submit(params, LoginContext.getLoginInfo(request));
        return AjaxResponse.of().setResultObj(payData);
    }

    /**
     * 前后台的分页列表
     *
     * @param productOrderQuery
     * @param request
     * @return the page list
     */
    @PostMapping({"/admin", "/user"})
    public PageList<ProductOrder> pageQuery(@RequestBody ProductOrderQuery productOrderQuery, HttpServletRequest request) {
        return productOrderService.queryPage(productOrderQuery, LoginContext.getLoginInfo(request));
    }

    /**
     * 查一个, 详情
     *
     * @param id id
     * @return
     */
    @GetMapping("/{id}")
    public ProductOrder queryById(@PathVariable Long id) {
        return productOrderService.findDetailById(id);
    }
}
