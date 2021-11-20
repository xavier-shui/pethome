package cn.xavier.org.mapper;

import cn.xavier.basic.mapper.BaseMapper;
import cn.xavier.org.domain.Employee;
import org.apache.ibatis.annotations.Param;

/**
 * 员工持久化层
 *
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    /**
     * 根据用户名，电话或邮箱查
     * @param
     * @return the employee
     */
    Employee loadByAdmin(@Param("username") String username,
                         @Param("phone") String phone,
                         @Param("email") String email);
}
