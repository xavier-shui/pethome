package cn.xavier.order.controller;

import cn.xavier.basic.util.AjaxResponse;
import cn.xavier.order.service.IProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/productOrder")
public class ProductOrderController {

    @Autowired
    private IProductOrderService productOrderService;
    @PostMapping("/submit")
    public AjaxResponse submit(@RequestBody Map<String,Object> params, HttpServletRequest request){

        // try{
        //     String payData = productOrderService.submit(params, UserContext.getUser(request));
        //     System.out.println(payData);
        //     return AjaxResponse.of().setResultObj(payData);
        // }catch (Exception e){
        //     e.printStackTrace();
        //     return AjaxResponse.of().setSuccess(false).setMessage("下单失败！"+e.getMessage());
        // }
        return null;

    }
}
