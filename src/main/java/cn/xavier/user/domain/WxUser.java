package cn.xavier.user.domain;

import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;

@Data
public class WxUser extends BaseDomain {
    private Long id;
    private String openid;
    private String nickname;
    private Integer sex;
    private String address;
    private String headimgurl;
    private String unionid;
    private Long user_id;


}
