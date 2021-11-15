package cn.xavier.org.service;

import cn.xavier.basic.util.PageList;
import cn.xavier.org.domain.Employee;
import cn.xavier.org.query.EmployeeQuery;

import java.util.List;

/**
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
public interface IEmployeeService {

    /**
     * Add *
     *
     * @param employee employee
     */
    void add(Employee employee);

    /**
     * Update *
     *
     * @param employee employee
     */
    void update(Employee employee);

    /**
     * Remove *
     *
     * @param id id
     */
    void remove(Long id);

    /**
     * Find by id employee
     *
     * @param id id
     * @return the employee
     */
    Employee findById(Long id);

    /**
     * Find all
     *
     * @return the list
     */
    List<Employee> findAll();

    /**
     * Query page
     *
     * @param employeeQuery employee query
     * @return the page list
     */
    PageList<Employee> queryPage(EmployeeQuery employeeQuery);

    /**
     * Batch remove
     *
     * @param ids ids
     */
    void batchRemove(List<Long> ids);
}
