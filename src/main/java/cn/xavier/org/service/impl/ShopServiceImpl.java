package cn.xavier.org.service.impl;

import cn.xavier.basic.exception.BusinessException;
import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.org.domain.Employee;
import cn.xavier.org.domain.Shop;
import cn.xavier.org.mapper.EmployeeMapper;
import cn.xavier.org.mapper.ShopMapper;
import cn.xavier.org.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
@Service
public class ShopServiceImpl extends BaseServiceImpl<Shop> implements IShopService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Override
    @Transactional
    public void settleIn(Shop shop) {
        Employee admin = shop.getAdmin();
        // 参数非空校验
        if (admin == null) {
            throw new BusinessException("管理员必需有");
        }
        String[] parameters = {
                shop.getName(),
                shop.getTel(),
                shop.getAddress(),
                admin.getUsername(),
                admin.getPhone(),
                admin.getEmail(),
                admin.getPassword()
        };
        for (String parameter : parameters) {
            if (StringUtils.isEmpty(parameter)) {
                throw new BusinessException("必要参数不能为空");
            }
        };

        // 密码一致性校验
        if (!admin.getPassword().equals(admin.getConfirmPassword())) {
            throw new BusinessException("两次输入密码不一致");
        }

        // 电话，邮箱，用户已注册校验
        Employee adminInDB = employeeMapper.loadByAdmin(admin.getUsername(),
                admin.getPhone(),
                admin.getEmail());
        if (adminInDB != null) {
            throw new BusinessException("用户名、手机或邮箱已经存在，如果忘记密码，请找回");
        }


        // 保存Employee, 返回自增主键
        employeeMapper.save(admin);
        // 保存Shop(必须有admin_id), 返回自增主键
        shop.setAdmin(admin);
        shopMapper.save(shop);
        // 修改Employee关联的shop_id
        admin.setShop_id(shop.getId());
        employeeMapper.update(admin);
    }
}
