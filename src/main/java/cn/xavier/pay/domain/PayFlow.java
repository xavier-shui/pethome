package cn.xavier.pay.domain;

import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;

@Data
public class PayFlow extends BaseDomain {

    private String createTime;
    private Long user_id;
    private String nickName;
    private Integer money;
    private Integer type;
    private String businessType;
    private Long businessKey;
    private String businessName;
    private Integer payChannel;
    private String payChannelName;
    private String note;
    private String digest;
    private String unionPaySeq;

}
