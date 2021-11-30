package cn.xavier.order.controller;

import cn.xavier.basic.util.AjaxResponse;
import cn.xavier.basic.util.LoginContext;
import cn.xavier.order.service.IAdoptOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
