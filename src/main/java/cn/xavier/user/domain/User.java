package cn.xavier.user.domain;

import cn.xavier.basic.constant.EmployeeAndUserStateConstants;
import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;

import java.util.Date;

/**
 * @author Zheng-Wei Shui
 * @date 11/22/2021
 */
@Data
public class User extends BaseDomain {
    private String username;
    private String email;
    private String phone;
    private String salt;
    private String password;
    private Integer state = EmployeeAndUserStateConstants.REGISTERED;
    private Integer age;
    private Date createtime = new Date();
    private String headImg;
    private Long logininfo_id;
}
