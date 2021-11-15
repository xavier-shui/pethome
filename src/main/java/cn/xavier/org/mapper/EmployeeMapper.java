package cn.xavier.org.mapper;

import cn.xavier.org.domain.Employee;
import cn.xavier.org.query.EmployeeQuery;

import java.util.List;

/**
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
public interface EmployeeMapper {

    /**
     * Save *
     *
     * @param employee employee
     */
    void save(Employee employee);

    /**
     * Update *
     *
     * @param employee employee
     */
    void update(Employee employee);

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
     * @return the employee
     */
    Employee loadById(Long id);

    /**
     * Load all list
     *
     * @return the list
     */
    List<Employee> loadAll();

    /**
     * Query count 高级+分页查询
     *
     * @param employeeQuery employee query
     * @return the
     */
    Long queryCount(EmployeeQuery employeeQuery);

    /**
     * Query data 高级+分页查询
     *
     * @param employeeQuery employee query
     * @return the list
     */
    List<Employee> queryData(EmployeeQuery employeeQuery);

    /**
     * 批量删除
     *
     * @param ids ids
     */
    void batchDelete(List<Long> ids);
}
