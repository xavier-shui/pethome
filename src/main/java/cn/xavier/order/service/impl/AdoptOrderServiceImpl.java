package cn.xavier.order.service.impl;

import cn.xavier.basic.constant.LoginInfoConstants;
import cn.xavier.basic.domain.LoginInfo;
import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.basic.util.CodeGenerateUtils;
import cn.xavier.basic.util.PageList;
import cn.xavier.basic.util.TypeConverterUtils;
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
import cn.xavier.quartz.constant.JobConstants;
import cn.xavier.quartz.service.IQuartzService;
import cn.xavier.quartz.util.QuartzJobInfo;
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

    @Autowired
    private IQuartzService quartzService;


    @Override
    public String submit(Map<String, Object> params, LoginInfo loginInfo) {

        Integer serviceMethod = Integer.valueOf(params.get("service_method").toString());
        Long addressId = Long.valueOf(params.get("address_id").toString());
        Long payMethod = Long.valueOf(params.get("pay_method").toString());
        Long petId = Long.valueOf(params.get("pet_id").toString());

        // ????????????

        // ??????????????????????????????

        Pet pet = petMapper.loadById(petId);

        // ??????????????????
        User user = userMapper.loadByLoginInfoId(loginInfo.getId());
        AdoptOrder order = initAdoptOrder(pet, user);
        adoptOrderMapper.save(order);//????????????id
        UserAddress userAddress = userAddressMapper.loadById(addressId);
        OrderAddress orderAddress = initOrderAddress(order, userAddress);
        // ????????????
        orderAddressMapper.save(orderAddress);

        // ???????????????
        PayBill bill = initBill(payMethod, pet, user, order);
        // ??????????????????
        payBillService.add(bill);

        // ??????????????????
        QuartzJobInfo jobInfo = new QuartzJobInfo();
        String jobName = JobConstants.jobNameConstruct(JobConstants.ADOPT_ORDER_PAYMENT_TIMEOUT, order.getPaySn());
        jobInfo.setJobName(jobName);
        jobInfo.setFireDate(TypeConverterUtils.LocalDateTime2Date(order.getLastPayTime()));
        // ??????????????????????????????????????????
        quartzService.addJob(jobInfo);

        // ??????????????????
        return payBillService.pay(bill);
    }

    private OrderAddress initOrderAddress(AdoptOrder order, UserAddress userAddress) {
        OrderAddress orderAddress = new OrderAddress();
        BeanUtils.copyProperties(userAddress, orderAddress);
        orderAddress.setOrder_id(order.getId());
        orderAddress.setOrderSn(order.getOrderSn());
        return orderAddress;
    }

    @Override
    public PageList<AdoptOrder> queryPage(AdoptOrderQuery adoptOrderQuery, LoginInfo loginInfo) {
        if (loginInfo.getType() == LoginInfoConstants.ADMIN) {
            adoptOrderQuery.setShop_id(employeeMapper.loadByLoginInfoId(loginInfo.getId()).getShop_id());
        } else { //????????????
            adoptOrderQuery.setUser_id(userMapper.loadByLoginInfoId(loginInfo.getId()).getId());
        }
        return super.queryPage(adoptOrderQuery);
    }

    @Override
    public AdoptOrder findDetailById(Long id) {
        return adoptOrderMapper.loadDetailById(id);
    }

    @Override
    public void cancelByQuartz(String paySn) {
        AdoptOrder adoptOrder = adoptOrderMapper.loadByPaySn(paySn);
        if (adoptOrder.getState() != PayConstants.PAID) { // ???????????????????????????
            // ?????????????????????????????????????????????????????????
            adoptOrder.setState(PayConstants.CANCELLED);
            adoptOrderMapper.update(adoptOrder);
        }
    }

    private PayBill initBill(Long payMethod, Pet pet, User user, AdoptOrder order) {
        PayBill bill = new PayBill();
        bill.setDigest("[??????]???" + pet.getName() + "??????????????????");
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


    private AdoptOrder initAdoptOrder(Pet pet, User user) {
        AdoptOrder order = new AdoptOrder();
        order.setDigest("[??????]???" + pet.getName() + "???????????????");
        order.setState(OrderConstants.TO_BE_PAID);
        order.setPrice(pet.getSaleprice());
        String orderSn = CodeGenerateUtils.generateOrderSn(user.getId());
        order.setOrderSn(orderSn);
        // ????????????????????????
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
