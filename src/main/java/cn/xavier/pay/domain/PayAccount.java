package cn.xavier.pay.domain;

import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;

import java.util.Date;

@Data
public class PayAccount extends BaseDomain {
        private Date createTime;
       private Date updateTime;
        private Long user_id;
        private Integer useableBalance;
        private Integer frozenBalance;
       private Integer creditBanance;
        private String payPassword;
}
