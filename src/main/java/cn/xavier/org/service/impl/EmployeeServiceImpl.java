package cn.xavier.org.service.impl;

import cn.xavier.basic.util.PageList;
import cn.xavier.org.domain.Employee;
import cn.xavier.org.mapper.EmployeeMapper;
import cn.xavier.org.query.EmployeeQuery;
import cn.xavier.org.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
@Service
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    @Transactional
    public void add(Employee employee) {
        employeeMapper.save(employee);
    }

    @Override
    @Transactional
    public void update(Employee employee) {
        employeeMapper.update(employee);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        employeeMapper.delete(id);
    }

    @Override
    public Employee findById(Long id) {
        return employeeMapper.loadById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeMapper.loadAll();
    }

    @Override
    public PageList<Employee> queryPage(EmployeeQuery employeeQuery) {
        Long totals = employeeMapper.queryCount(employeeQuery);
        // 提高查库效率
        if (totals == 0) {
            return new PageList<>();
        }
        List<Employee> rows = employeeMapper.queryData(employeeQuery);
        return new PageList<>(totals, rows);
    }

    @Override
    @Transactional
    public void batchRemove(List<Long> ids) {
        employeeMapper.batchDelete(ids);
    }
}
