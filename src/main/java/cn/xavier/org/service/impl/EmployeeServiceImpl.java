package cn.xavier.org.service.impl;

import cn.xavier.basic.domain.LoginInfo;
import cn.xavier.basic.mapper.LoginInfoMapper;
import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.basic.util.MD5Utils;
import cn.xavier.basic.util.StrUtils;
import cn.xavier.org.domain.Employee;
import cn.xavier.org.mapper.EmployeeMapper;
import cn.xavier.org.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.xavier.basic.util.TypeConverterUtils.toLoginInfo;

/**
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {

    @Autowired
    private LoginInfoMapper loginInfoMapper;

    private EmployeeMapper employeeMapper = (EmployeeMapper) super.mapper;


    @Override
    public void add(Employee employee) {
        // 密码处理
        initEmployee(employee);

        // 生成loginInfo
        LoginInfo loginInfo = toLoginInfo(employee);

        // 保存loginInfo，需要自增ID
        loginInfoMapper.save(loginInfo);
        employee.setLogininfo_id(loginInfo.getId());
        // 保存employee
        employeeMapper.save(employee);
    }


    private void initEmployee(Employee employee) {
        String salt = StrUtils.getComplexRandomString(32);
        String md5Password = MD5Utils.encrypByMd5(employee.getPassword() + salt);
        employee.setSalt(salt);
        employee.setPassword(md5Password);

    }

    // update 和 remove 需要关联修改t_logininfo表
    @Override
    public void update(Employee employee) {
        loginInfoMapper.update(toLoginInfo(employee));
        super.update(employee);
    }

    @Override
    public void remove(Long id) {
        loginInfoMapper.delete(employeeMapper.loadById(id).getLogininfo_id());
        super.remove(id);
    }
}
