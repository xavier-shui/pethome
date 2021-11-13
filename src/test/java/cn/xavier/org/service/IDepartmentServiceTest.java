package cn.xavier.org.service;

import cn.xavier.BaseTest;
import cn.xavier.org.domain.Department;
import cn.xavier.org.domain.Employee;
import cn.xavier.org.query.DepartmentQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IDepartmentServiceTest extends BaseTest {

    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void add() {
        Department department = departmentService.findById(27L);
        department.setId(null);
        Employee employee = new Employee();
        employee.setId(324L);
        department.setManager(employee);
        departmentService.add(department);
    }

    @Test
    public void update() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void queryPage() {
        DepartmentQuery departmentQuery = new DepartmentQuery();
        departmentQuery.setCurrentPage(2);
        departmentQuery.setPageSize(3);
        departmentService.queryPage(departmentQuery).getRows().forEach(System.out::println);
    }
}