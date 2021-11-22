package cn.xavier.user.domain;

import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;

/**
 * 登录信息， 反3NF， 变多表为单表查询
 *
 * @author Zheng-Wei Shui
 * @date 11/22/2021
*/

@Data
public class LoginInfo extends BaseDomain {
    private String username;
    private String phone;
    private String email;
    private String salt;
    private String password;
    // 0 代表管理员 1 用户
    private Integer type;
    // 0 不可以用 1 可用
    private Integer disable;
}
