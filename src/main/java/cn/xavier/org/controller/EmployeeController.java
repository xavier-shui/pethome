package cn.xavier.org.controller;

import cn.xavier.basic.util.AjaxResponse;
import cn.xavier.basic.util.PageList;
import cn.xavier.org.domain.Employee;
import cn.xavier.org.query.EmployeeQuery;
import cn.xavier.org.service.IEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工的RestFul接口
 *
 * @author Zheng -Wei Shui
 * @date 11 /12/2021
 */
@RestController
@RequestMapping("/employee")
@Api(tags = "员工接口",description = "员工接口详细描述")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    /**
     * 新增或修改员工
     *
     * @return the ajax response
     */
    @PutMapping
    @ApiOperation(value = "员工添加或修改",notes = "如果有id就是修改否则就是添加")
    public AjaxResponse addOrUpdate(@RequestBody Employee employee) {
        if (employee.getId() == null) {
            employeeService.add(employee);
        } else {
            employeeService.update(employee);
        }
        return null;
    }

    /**
     * Remove by id , {}内为占位符
     * 一个参数，pathvariable可以不写value
     * @return the ajax response
     */
    @DeleteMapping("/{id}")
    public AjaxResponse removeById(@PathVariable Long id) {
        employeeService.remove(id);
        return null;
    }

    /**
     * 查一个
     *
     * @param id id
     * @return the employee
     */
    @GetMapping("/{id}")
    public Employee queryById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    /**
     * 查全部
     *
     * @return the employee
     */
    @GetMapping
    public List<Employee> queryAll() {
        return employeeService.findAll();
    }

    /**
     * 分页 + 高级查询
     *
     * @param employeeQuery employee query
     * @return the page list
     */
    @PostMapping("/list")
    public PageList<Employee> queryPage(@RequestBody EmployeeQuery employeeQuery) {
        return employeeService.queryPage(employeeQuery);
    }

    /**
     * 批量删除员工
     *
     * @param ids ids
     * @return the ajax response
     */
    @PatchMapping
    public AjaxResponse batchRemove(@RequestBody List<Long> ids) {
        employeeService.batchRemove(ids);
        return null;
    }


}
