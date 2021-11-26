package cn.xavier.pet.service.impl;

import cn.xavier.basic.constant.LoginInfoConstants;
import cn.xavier.basic.domain.LoginInfo;
import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.basic.util.DistanceUtil;
import cn.xavier.basic.util.PageList;
import cn.xavier.org.domain.Shop;
import cn.xavier.org.mapper.EmployeeMapper;
import cn.xavier.org.mapper.ShopMapper;
import cn.xavier.pet.domain.SearchMasterMsg;
import cn.xavier.pet.mapper.SearchMasterMsgMapper;
import cn.xavier.pet.query.SearchMasterMsgQuery;
import cn.xavier.pet.service.ISearchMasterMsgService;
import cn.xavier.user.domain.User;
import cn.xavier.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchMasterMsgServiceImpl extends BaseServiceImpl<SearchMasterMsg> implements ISearchMasterMsgService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private SearchMasterMsgMapper searchMasterMsgMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public void publish(SearchMasterMsg searchMasterMsg, LoginInfo loginInfo) {
        // 参数校验

        // 获取用户
        User user = userMapper.loadByLoginInfoId(loginInfo.getId());
        // 取得最近的店铺
        Shop nearestShop = DistanceUtil.getNearestShop(DistanceUtil.getPoint(searchMasterMsg.getAddress()), shopMapper.loadAll());
        // 从店铺取得店长id，关联查询店长联系方式，通知他

        // 保存
        searchMasterMsg.setUser_id(user.getId());
        searchMasterMsg.setShop_id(nearestShop.getId());
        searchMasterMsgMapper.save(searchMasterMsg);
    }

    @Override
    public PageList<SearchMasterMsg> list(SearchMasterMsgQuery query, LoginInfo loginInfo) {
        if (loginInfo.getType() == LoginInfoConstants.ADMIN) {
            query.setShop_id(employeeMapper.loadByLoginInfoId(loginInfo.getId()).getShop_id()); // 如果是平台人员会设成null， 店长或店员有值
        } else { //前台用户
            query.setUser_id(userMapper.loadByLoginInfoId(loginInfo.getId()).getId());
        }
        return super.queryPage(query);
    }
//    private PetAcquisitionOrder pet2order(Pet pet, SearchMasterMsg adopt) {
//        PetAcquisitionOrder order = new PetAcquisitionOrder();
//        order.setDigest("[摘要]对"+pet.getName()+"收购订单！");
//        order.setState(0);//待支付
//        order.setPrice(pet.getCostprice());
//        order.setAddress(adopt.getAddress());
//        String orderSn = CodeGenerateUtils.generateOrderSn(adopt.getUser_id());
//        order.setOrderSn(orderSn);
//        order.setPet_id(pet.getId());
//        order.setUser_id(adopt.getUser_id());
//        order.setPaytype(0);
//        order.setShop_id(pet.getShop_id());
//        order.setEmp_id(EmployeeContext.getLoginEployee().getId());
//        return order;
//    }
}
