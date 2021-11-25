package cn.xavier.basic.util;

import cn.xavier.basic.constant.LoginInfoConstants;
import cn.xavier.basic.domain.BaseDomain;
import cn.xavier.basic.domain.LoginInfo;
import cn.xavier.org.domain.Employee;
import cn.xavier.user.domain.User;
import org.springframework.beans.BeanUtils;

/**
 * @author Zheng-Wei Shui
 * @date 11/25/2021
 */
public class TypeConverterUtils {

    /**
     * 用户或员工转loginInfo
     *
     * @param employeeOrUser employee or user
     * @return the login info
     */
    public static LoginInfo toLoginInfo(BaseDomain employeeOrUser) {
        LoginInfo loginInfo = new LoginInfo();
        BeanUtils.copyProperties(employeeOrUser, loginInfo);
        // 设置类型
        if (employeeOrUser.getClass().equals(Employee.class)) {
            loginInfo.setType(LoginInfoConstants.ADMIN);
        } else if (employeeOrUser.getClass().equals(User.class)) {
            loginInfo.setType(LoginInfoConstants.USER);
        }
        return loginInfo;
    }
}
