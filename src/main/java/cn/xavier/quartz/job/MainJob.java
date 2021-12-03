package cn.xavier.quartz.job;

import cn.xavier.order.service.IAdoptOrderService;
import cn.xavier.order.service.IProductOrderService;
import cn.xavier.pay.service.IPayBillService;
import cn.xavier.quartz.constant.JobConstants;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 各种订单支付和确认定时共用
 * @author Zheng-Wei Shui
 * @date 12/2/2021
 */
@Slf4j
public class MainJob implements Job {

    // 此处可以直接注入，因为实例对象是每次scheduler执行这个任务时生成的
    @Autowired
    private IAdoptOrderService adoptOrderService;

    @Autowired
    private IPayBillService payBillService;

    @Autowired
    private IProductOrderService productOrderService;


    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // 包含了作业类型和paySn, :分割
        String jobName = context.getJobDetail().getKey().getName();
        String jobType = jobName.split(":")[0];
        String paySn = jobName.split(":")[1];
        // 取消订单
        switch (jobType) {
            case JobConstants.ADOPT_ORDER_PAYMENT_TIMEOUT: {
                adoptOrderService.cancelByQuartz(paySn);
                break;
            }
            case JobConstants.PRODUCT_ORDER_PAYMENT_TIMEOUT: {
                productOrderService.cancelByQuartz(paySn);
                break;
            }
            default: break;
        }
        // 取消支付单
        payBillService.cancelByQuartz(paySn);
        log.debug("支付：{} 超时已自动取消", paySn);
    }
}
