package cn.xavier.order.controller;

import cn.xavier.basic.util.PageList;
import cn.xavier.order.domain.PetAcquisitionOrder;
import cn.xavier.order.query.PetAcquisitionOrderQuery;
import cn.xavier.order.service.IPetAcquisitionOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/petAcquisitionOrder")
public class PetAcquisitionOrderController {
    @Autowired
    private IPetAcquisitionOrderService petAcquisitionOrderService;
    @PostMapping
    public PageList<PetAcquisitionOrder> list(@RequestBody PetAcquisitionOrderQuery query){
        //员工只能查看到自己收购订单
        // query.setHandlerId(EmployeeContext.getLoginEployee().getId());
        // return  petAcquisitionOrderService.queryPage(query);
        return null;
    }
}
