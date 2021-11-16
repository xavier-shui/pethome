package cn.xavier.org.domain;

import cn.xavier.basic.domain.BaseDomain;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门实体类
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
@Data
public class Department extends BaseDomain {
    // 部门编号
    private String sn;
    private String name;
    // 上级部门路径
    @ApiModelProperty("上级部门的层级路径")
    private String dirPath;
    // 状态
    private Integer state;
    // 管理人
    private Employee manager;
    private Long manager_id;
    // 上级部门
    private Department parent;
    private Long parent_id;

    private List<Department> children = new ArrayList<>();
}
