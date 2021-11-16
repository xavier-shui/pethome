package cn.xavier.org.service.impl;

import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.org.domain.Department;
import cn.xavier.org.mapper.DepartmentMapper;
import cn.xavier.org.service.IDepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements IDepartmentService {

    // 此处可以注入DepartmentMapper，便可以使用其特有的方法
    // 也可以不注入，父类mapper字段改为了protected，可以直接使用, 需使用特有方法时可强转(因为已经注入BaseMapper对应特定类的子类型对象)

    // @Transactional //对于可以继承的注解，子类即使覆写了方法，注解也同样可继承到。
    @Override
    public void add(Department department) {
        mapper.save(department);
        this.update(department);
    }

    @Override
    public void update(Department department) {
        // 追加dirPath
        String dirPath = "";
        if (department.getParent() == null) { // 顶部门
            dirPath = "/" + department.getId();
        } else {
            Long parentId = department.getParent().getId();
            Department parentDepartment = mapper.loadById(parentId);
            dirPath = parentDepartment.getDirPath() + "/" + department.getId();
        }
        department.setDirPath(dirPath);
        mapper.update(department);
    }

    @Override
    public List<Department> queryTree() {
        return ((DepartmentMapper)mapper).loadTree();
    }
}
