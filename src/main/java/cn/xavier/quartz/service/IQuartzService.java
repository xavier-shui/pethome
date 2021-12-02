package cn.xavier.quartz.service;

import cn.xavier.quartz.util.QuartzJobInfo; /**
 * @author Zheng-Wei Shui
 * @date 12/2/2021
 */
public interface IQuartzService {
    /**
     * 对外暴露添加定时任务接口
     *
     * @param jobInfo job info
     */
    void addJob(QuartzJobInfo jobInfo);

    /**
     * 对外暴露移除定时任务接口
     *
     * @param jobName job name
     */
    void removeJob(String jobName);
}
