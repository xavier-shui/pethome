package cn.xavier.order.controller;

import cn.xavier.basic.util.AjaxResponse;
import cn.xavier.basic.util.LoginContext;
import cn.xavier.basic.util.PageList;
import cn.xavier.order.domain.AdoptOrder;
import cn.xavier.order.query.AdoptOrderQuery;
import cn.xavier.order.service.IAdoptOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/adoptOrder")
public class AdoptOrderController {

    @Autowired
    private IAdoptOrderService adoptOrderService;

    /**
     * Submit 客户领养订单提交
     *
     * @param params  params
     * @param request request
     * @return the ajax response
     */
    @PostMapping("/submit")
    public AjaxResponse submit(@RequestBody Map<String,Object> params, HttpServletRequest request){
        String payData = adoptOrderService.submit(params, LoginContext.getLoginInfo(request));
        return AjaxResponse.of().setResultObj(payData);
    }

    /**
     * 前后台的分页列表
     *
     * @param adoptOrderQuery adopt order query
     * @return the page list
     */
    @PostMapping({"/admin", "/user"})
    public PageList<AdoptOrder> pageQuery(@RequestBody AdoptOrderQuery  adoptOrderQuery, HttpServletRequest request) {
        return adoptOrderService.queryPage(adoptOrderQuery, LoginContext.getLoginInfo(request));
    }

    /**
     * 查一个, 详情
     *
     * @param id id
     * @return
     */
    @GetMapping("/{id}")
    public AdoptOrder queryById(@PathVariable Long id) {
        return adoptOrderService.findDetailById(id);
    }
}
