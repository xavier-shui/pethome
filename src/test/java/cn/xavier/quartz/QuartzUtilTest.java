package cn.xavier.quartz;

import cn.xavier.BaseTest;
import cn.xavier.quartz.util.QuartzUtils;
import org.junit.Test;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class QuartzUtilTest extends BaseTest {

    @Autowired
    private SchedulerFactoryBean factoryBean;

    @Test
    public void test() throws Exception{
        System.out.println(factoryBean);

        //1 创建定时任务
        Scheduler sched = factoryBean.getScheduler();
        String jobName = "myJob";
        Class cls = PrintTimeJob.class;
//        Object params = "zs"; //传字符串 //传对象
        Object params = new User(1L,"zs"); //传字符串 //传对象
        String time = "0/1 * * * * ?";
        QuartzUtils.addJob(sched,jobName,cls,params,time);
        //2 睡眠一下
        Thread.sleep(10000);
        //3 删除定时任务
        QuartzUtils.removeJob(sched,jobName);


        //让程序不结束
        Thread.sleep(100000000);

    }

    // 为了启动测试环境看定时任务
    @Test
    public void test2() throws InterruptedException {
        Thread.sleep(100000000);
    }


}