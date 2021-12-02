package cn.xavier.quartz;

import cn.xavier.BaseTest;
import cn.xavier.basic.util.TypeConverterUtils;
import cn.xavier.quartz.constant.JobConstants;
import cn.xavier.quartz.service.IQuartzService;
import cn.xavier.quartz.util.QuartzJobInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * @author Zheng-Wei Shui
 * @date 12/2/2021
 */
public class IQuartzServiceTest extends BaseTest {

    @Autowired
    private IQuartzService quartzService;
    
    @Test
    public void testRemoveJob() {
        quartzService.removeJob("adopt_order_payment_timeout:211202110795344");
    }

    @Test
    public void testAddJob() throws InterruptedException {
        QuartzJobInfo jobInfo = new QuartzJobInfo();
        String jobName = JobConstants.ADOPT_ORDER_PAYMENT_TIMEOUT + "211202110795344";
        jobInfo.setJobName(jobName);
        jobInfo.setFireDate(TypeConverterUtils.LocalDateTime2Date(LocalDateTime.now().plusMinutes(1)));
        quartzService.addJob(jobInfo);
        Thread.sleep(1000 * 1000);
    }
}
