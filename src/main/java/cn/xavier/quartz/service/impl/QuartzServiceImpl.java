package cn.xavier.quartz.service.impl;

import cn.xavier.quartz.job.MainJob;
import cn.xavier.quartz.service.IQuartzService;
import cn.xavier.quartz.util.QuartzJobInfo;
import cn.xavier.quartz.util.QuartzUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author Zheng-Wei Shui
 * @date 12/2/2021
 */
@Service
@Slf4j
public class QuartzServiceImpl implements IQuartzService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    private Scheduler sched;

    // 初始化值
    @PostConstruct
    private void init() {
        sched = schedulerFactoryBean.getScheduler();
    }


    @Override
    public void addJob(QuartzJobInfo jobInfo) {
        QuartzUtils.addJob(sched, jobInfo.getJobName(), MainJob.class, jobInfo.getParams(), jobInfo.getCronExpression());
        log.debug("添加定时任务: {}", jobInfo.getJobName());
    }

    @Override
    public void removeJob(String jobName) {
        QuartzUtils.removeJob(sched, jobName);
        log.debug("移除定时任务: {}", jobName);
    }
}
