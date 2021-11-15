package cn.xavier.org.service.impl;

import cn.xavier.basic.util.PageList;
import cn.xavier.org.domain.Department;
import cn.xavier.org.mapper.DepartmentMapper;
import cn.xavier.org.query.DepartmentQuery;
import cn.xavier.org.service.IDepartmentService;
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
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    @Transactional
    public void add(Department department) {
        departmentMapper.save(department);
        this.update(department);
    }

    @Override
    @Transactional
    public void update(Department department) {
        // 追加dirPath
        String dirPath = "";
        if (department.getParent() == null) { // 顶部门
            dirPath = "/" + department.getId();
        } else {
            Long parentId = department.getParent().getId();
            Department parentDepartment = departmentMapper.loadById(parentId);
            dirPath = parentDepartment.getDirPath() + "/" + department.getId();
        }
        department.setDirPath(dirPath);
        departmentMapper.update(department);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        departmentMapper.delete(id);
    }

    @Override
    public Department findById(Long id) {
        return departmentMapper.loadById(id);
    }

    @Override
    public List<Department> findAll() {
        return departmentMapper.loadAll();
    }

    @Override
    public PageList<Department> queryPage(DepartmentQuery departmentQuery) {
        Long totals = departmentMapper.queryCount(departmentQuery);
        // 提高查库效率
        if (totals == 0) {
            return new PageList<>();
        }
        List<Department> rows = departmentMapper.queryData(departmentQuery);
        return new PageList<>(totals, rows);
    }

    @Override
    @Transactional
    public void batchRemove(List<Long> ids) {
        departmentMapper.batchDelete(ids);
    }

    @Override
    public List<Department> queryTree() {
        return departmentMapper.loadTree();
    }
}
