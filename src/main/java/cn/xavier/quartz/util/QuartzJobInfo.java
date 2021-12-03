package cn.xavier.quartz.util;

import lombok.Data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Data
public class QuartzJobInfo implements Serializable {
    private String jobName;
    private Map<String, Object> params;
    private String cronExpression;
    private Date fireDate;

    /**
     * Sets fire date Date转cron表达式
     *
     * @param fireDate fire date
     */
    public void setFireDate(Date fireDate) {
        this.fireDate = fireDate;
        String[] cronArr = new String[7];
        for (int i = 0; i < cronArr.length; i++) {
            cronArr[i] = "";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fireDate);
        int second = calendar.get(Calendar.SECOND);
        int minute = calendar.get(Calendar.MINUTE);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);

        cronArr[0] = second + "";
        cronArr[1] = minute + "";
        cronArr[2] = hour + "";

        cronArr[3] = day + "";
        cronArr[4] = month + "";
        cronArr[5] = "?";
        cronArr[6] = year + "";

        String cron = String.join(" ", cronArr).trim();
        this.setCronExpression(cron);
    }

}