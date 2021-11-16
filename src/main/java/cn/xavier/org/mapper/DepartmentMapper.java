package cn.xavier.org.mapper;

import cn.xavier.basic.mapper.BaseMapper;
import cn.xavier.org.domain.Department;

import java.util.List;

/**
 * 部门持久化层
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
public interface DepartmentMapper extends BaseMapper<Department> {
    // 1. 基本CRUD直接继承

    // 2. 特有的方法自己写
    List<Department> loadTree();
}
