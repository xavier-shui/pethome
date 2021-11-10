package cn.xavier.org.mapper;

import cn.xavier.org.domain.Department;
import cn.xavier.org.query.DepartmentQuery;

import java.util.List;

/**
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
public interface DepartmentMapper {

    /**
     * Save *
     *
     * @param department department
     */
    void save(Department department);

    /**
     * Update *
     *
     * @param department department
     */
    void update(Department department);

    /**
     * Delete *
     *
     * @param id id
     */
    void delete(Long id);

    /**
     * Load by id
     *
     * @param id id
     * @return the department
     */
    Department loadById(Long id);

    /**
     * Load all list
     *
     * @return the list
     */
    List<Department> loadAll();

    /**
     * Query count 高级+分页查询
     *
     * @param departmentQuery department query
     * @return the
     */
    Long queryCount(DepartmentQuery departmentQuery);

    /**
     * Query data 高级+分页查询
     *
     * @param departmentQuery department query
     * @return the list
     */
    List<Department> queryData(DepartmentQuery departmentQuery);

}
