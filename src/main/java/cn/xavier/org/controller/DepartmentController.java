package cn.xavier.org.controller;

import cn.xavier.basic.util.AjaxResponse;
import cn.xavier.basic.util.PageList;
import cn.xavier.org.domain.Department;
import cn.xavier.org.query.DepartmentQuery;
import cn.xavier.org.service.IDepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门的RestFul接口
 *
 * @author Zheng -Wei Shui
 * @date 11 /12/2021
 */
@RestController
@RequestMapping("/dept")
@Api(tags = "部门接口",description = "部门接口详细描述")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    /**
     * 新增或修改部门
     *
     * @return the ajax response
     */
    @PutMapping
    @ApiOperation(value = "部门添加或修改",notes = "如果有id就是修改否则就是添加")
    public AjaxResponse addOrUpdate(@RequestBody Department department) {
        if (department.getId() == null) {
            departmentService.add(department);
        } else {
            departmentService.update(department);
        }
        return AjaxResponse.of();
    }

    /**
     * Remove by id , {}内为占位符
     * 一个参数，pathvariable可以不写value
     * @return the ajax response
     */
    @DeleteMapping("/{id}")
    public AjaxResponse removeById(@PathVariable Long id) {
        departmentService.remove(id);
        return AjaxResponse.of();
    }

    /**
     * 查一个
     *
     * @param id id
     * @return the department
     */
    @GetMapping("/{id}")
    public Department queryById(@PathVariable Long id) {
        return departmentService.findById(id);
    }

    /**
     * 查全部
     *
     * @return the department
     */
    @GetMapping
    public List<Department> queryAll() {
        return departmentService.findAll();
    }

    /**
     * 分页 + 高级查询
     *
     * @param departmentQuery department query
     * @return the page list
     */
    @PostMapping("/list")
    public PageList<Department> queryPage(@RequestBody DepartmentQuery departmentQuery) {
         return departmentService.queryPage(departmentQuery);
    }

    /**
     * 批量删除部门
     *
     * @param ids ids
     * @return the ajax response
     */
    @PatchMapping
    public AjaxResponse batchRemove(@RequestBody List<Long> ids) {
        departmentService.batchRemove(ids);
        return AjaxResponse.of();
    }

    /**
     * Query tree 查部门树, 用于级联菜单
     *
     * @return the list
     */
    @GetMapping("/tree")
    public List<Department> queryTree() {
        return departmentService.queryTree();
    }


}
