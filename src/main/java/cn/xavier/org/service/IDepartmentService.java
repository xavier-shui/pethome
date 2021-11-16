package cn.xavier.org.service;

import cn.xavier.basic.service.IBaseService;
import cn.xavier.org.domain.Department;

import java.util.List;

/**
 * 部门业务层
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
public interface IDepartmentService extends IBaseService<Department> {
    // 1. 基本CRUD直接继承

    // 2. 特有的方法自己写
    List<Department> queryTree();
}
