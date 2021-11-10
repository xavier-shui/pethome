package cn.xavier.org.service;

import cn.xavier.basic.util.PageList;
import cn.xavier.org.domain.Department;
import cn.xavier.org.query.DepartmentQuery;

import java.util.List;

/**
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
public interface IDepartmentService {

    /**
     * Add *
     *
     * @param department department
     */
    void add(Department department);

    /**
     * Update *
     *
     * @param department department
     */
    void update(Department department);

    /**
     * Remove *
     *
     * @param id id
     */
    void remove(Long id);

    /**
     * Find by id department
     *
     * @param id id
     * @return the department
     */
    Department findById(Long id);

    /**
     * Find all
     *
     * @return the list
     */
    List<Department> findAll();

    /**
     * Query page
     *
     * @param departmentQuery department query
     * @return the page list
     */
    PageList<Department> queryPage(DepartmentQuery departmentQuery);
}
