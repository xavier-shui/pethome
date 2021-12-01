package cn.xavier.order.service.impl;

import cn.xavier.basic.constant.LoginInfoConstants;
import cn.xavier.basic.domain.LoginInfo;
import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.basic.util.CodeGenerateUtils;
import cn.xavier.basic.util.PageList;
import cn.xavier.order.constant.OrderConstants;
import cn.xavier.order.domain.AdoptOrder;
import cn.xavier.order.domain.OrderAddress;
import cn.xavier.order.mapper.AdoptOrderMapper;
import cn.xavier.order.mapper.OrderAddressMapper;
import cn.xavier.order.query.AdoptOrderQuery;
import cn.xavier.order.service.IAdoptOrderService;
import cn.xavier.org.mapper.EmployeeMapper;
import cn.xavier.pay.constants.PayConstants;
import cn.xavier.pay.domain.PayBill;
import cn.xavier.pay.service.IPayBillService;
import cn.xavier.pet.domain.Pet;
import cn.xavier.pet.mapper.PetMapper;
import cn.xavier.pet.service.IPetService;
import cn.xavier.user.domain.User;
import cn.xavier.user.domain.UserAddress;
import cn.xavier.user.mapper.UserAddressMapper;
import cn.xavier.user.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class AdoptOrderServiceImpl extends BaseServiceImpl<AdoptOrder> implements IAdoptOrderService {

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private UserMapper userMapper;

    // SPEL
    @Value("${order.timeout.minutes}")
    private Integer orderTimeoutMinutes;

    @Autowired
    private AdoptOrderMapper adoptOrderMapper;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private OrderAddressMapper orderAddressMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private IPetService petService;

    @Autowired
    private IPayBillService payBillService;


    @Override
    public String submit(Map<String, Object> params, LoginInfo loginInfo) {

        Integer serviceMethod = Integer.valueOf(params.get("service_method").toString());
        Long addressId = Long.valueOf(params.get("address_id").toString());
        Long payMethod = Long.valueOf(params.get("pay_method").toString());
        Long petId = Long.valueOf(params.get("pet_id").toString());

        // 参数校验

        // 宠物等支付成功再下架

        Pet pet = petMapper.loadById(petId);

        // 生成领养订单
        User user = userMapper.loadByLoginInfoId(loginInfo.getId());
        AdoptOrder order = initAdoptOrder(pet, user);
        adoptOrderMapper.save(order);//返回自增id
        UserAddress userAddress = userAddressMapper.loadById(addressId);
        OrderAddress orderAddress = initOrderAddress(order, userAddress);
        // 保存订单
        orderAddressMapper.save(orderAddress);

        // 生成支付单
        PayBill bill = initBill(payMethod, pet, user, order);
        // 持久化支付单
        payBillService.add(bill);
        // 调用支付接口
        return payBillService.pay(bill);
    }

    @Override
    public PageList<AdoptOrder> queryPage(AdoptOrderQuery adoptOrderQuery, LoginInfo loginInfo) {
        if (loginInfo.getType() == LoginInfoConstants.ADMIN) {
            adoptOrderQuery.setShop_id(employeeMapper.loadByLoginInfoId(loginInfo.getId()).getShop_id());
        } else { //前台用户
            adoptOrderQuery.setUser_id(userMapper.loadByLoginInfoId(loginInfo.getId()).getId());
        }
        return super.queryPage(adoptOrderQuery);
    }

    @Override
    public AdoptOrder findDetailById(Long id) {
        return adoptOrderMapper.loadDetailById(id);
    }

    private PayBill initBill(Long payMethod, Pet pet, User user, AdoptOrder order) {
        PayBill bill = new PayBill();
        bill.setDigest("[摘要]对" + pet.getName() + "领养支付单！");
        bill.setMoney(order.getPrice());
        bill.setUnionPaySn(order.getPaySn());
        bill.setState(PayConstants.TO_BE_PAID);
        bill.setLastPayTime(order.getLastPayTime());
        bill.setPayChannel(payMethod);
        bill.setBusinessType(PayConstants.BUSINESS_TYPE_ADOPT);
        bill.setBusinessKey(order.getId());
        bill.setUser_id(user.getId());
        bill.setShop_id(order.getShop_id());
        bill.setNickName(user.getUsername());
        return bill;
    }


    private OrderAddress initOrderAddress(AdoptOrder order, UserAddress userAddress) {
        OrderAddress orderAddress = new OrderAddress();
        BeanUtils.copyProperties(userAddress, orderAddress);
        orderAddress.setOrder_id(order.getId());
        orderAddress.setOrderSn(order.getOrderSn());
        return orderAddress;
    }

    private AdoptOrder initAdoptOrder(Pet pet, User user) {
        AdoptOrder order = new AdoptOrder();
        order.setDigest("[摘要]对" + pet.getName() + "领养订单！");
        order.setState(OrderConstants.TO_BE_PAID);
        order.setPrice(pet.getSaleprice());
        String orderSn = CodeGenerateUtils.generateOrderSn(user.getId());
        order.setOrderSn(orderSn);
        // 方便后面支付单用
        order.setPaySn(CodeGenerateUtils.generateUnionPaySn());
        order.setPet_id(pet.getId());
        order.setUser_id(user.getId());
        order.setShop_id(pet.getShop_id());
        // order.setLastPayTime(Date.from(LocalDateTime.now().plusMinutes(orderTimeoutMinutes).atZone(ZoneId
        //         .systemDefault()).toInstant()));
        order.setLastPayTime(LocalDateTime.now().plusMinutes(orderTimeoutMinutes));
        return order;
    }
}
