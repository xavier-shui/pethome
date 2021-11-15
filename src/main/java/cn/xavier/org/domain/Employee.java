package cn.xavier.org.domain;

import lombok.Data;

/**
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
@Data
public class Employee {
    private Long id;
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
