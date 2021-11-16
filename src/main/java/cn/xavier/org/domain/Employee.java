package cn.xavier.org.domain;

import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;

/**
 * 员工实体类
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
@Data
public class Employee extends BaseDomain {
    private String username;
    private String email;
    private String phone;
    // 盐值
    private String salt;
    private String password;
    private Integer age;
    private Integer state;


    private Long department_id;
    private Long logininfo_id;
    private Long shop_id;
}
