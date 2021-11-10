package cn.xavier.org.domain;

import lombok.Data;

/**
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
@Data
public class Department {
    private Long id;
    // 部门编号
    private String sn;
    private String name;
    // 上级部门路径
    private String dirPath;
    // 状态
    private Integer state;
    // 管理人
    private Employee manager;
    private Department parent;
}
