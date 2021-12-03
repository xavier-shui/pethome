package cn.xavier.order.service.impl;

import cn.xavier.basic.constant.LoginInfoConstants;
import cn.xavier.basic.domain.LoginInfo;
import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.basic.util.CodeGenerateUtils;
import cn.xavier.basic.util.PageList;
import cn.xavier.basic.util.TypeConverterUtils;
import cn.xavier.order.constant.OrderConstants;
import cn.xavier.order.domain.OrderAddress;
import cn.xavier.order.domain.ProductOrder;
import cn.xavier.order.domain.ProductOrderDetail;
import cn.xavier.order.mapper.OrderAddressMapper;
import cn.xavier.order.mapper.ProductOrderMapper;
import cn.xavier.order.query.ProductOrderQuery;
import cn.xavier.order.service.IProductOrderDetailService;
import cn.xavier.order.service.IProductOrderService;
import cn.xavier.org.mapper.EmployeeMapper;
import cn.xavier.pay.constants.PayConstants;
import cn.xavier.pay.domain.PayBill;
import cn.xavier.pay.service.IPayBillService;
import cn.xavier.product.domain.Product;
import cn.xavier.product.mapper.ProductMapper;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class ProductOrderServiceImpl extends BaseServiceImpl<ProductOrder> implements IProductOrderService {

    @Autowired
    private ProductOrderMapper productOrderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private UserMapper userMapper;

    @Value("${order.timeout.minutes}")
    private Integer orderTimeoutMinutes;

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private OrderAddressMapper orderAddressMapper;

    @Autowired
    private IPayBillService payBillService;

    @Autowired
    private IQuartzService quartzService;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private IProductOrderDetailService productOrderDetailService;


    @Override
    public String submit(Map<String, Object> params, LoginInfo loginInfo) {
        Integer serviceMethod = Integer.valueOf(params.get("service_method").toString());
        Long addressId = Long.valueOf(params.get("address_id").toString());
        Long payMethod = Long.valueOf(params.get("pay_method").toString());
        Long productId = Long.valueOf(params.get("product_id").toString());
        BigDecimal quantity = new BigDecimal(params.get("quantity").toString());

        // 参数校验

        // 销量更新
        Product product = productMapper.loadById(productId);
        product.setSalecount(product.getSalecount() + quantity.longValue());
        productMapper.update(product);

        // 生成服务订单
        User user = userMapper.loadByLoginInfoId(loginInfo.getId());
        ProductOrder productOrder = initProductOrder(product, user, quantity);
        productOrderMapper.save(productOrder);//返回自增id

        // 生成服务订单详情
        ProductOrderDetail productOrderDetail = initProductOrderDetail(productOrder, product);
        productOrderDetailService.add(productOrderDetail);

        UserAddress userAddress = userAddressMapper.loadById(addressId);
        OrderAddress orderAddress = initOrderAddress(productOrder, userAddress);
        // 保存订单地址
        orderAddressMapper.save(orderAddress);

        // 生成支付单
        PayBill bill = initBill(payMethod, product, user, productOrder);
        // 持久化支付单
        payBillService.add(bill);

        // 添加定时任务
        QuartzJobInfo jobInfo = new QuartzJobInfo();
        String jobName = JobConstants.jobNameConstruct(JobConstants.PRODUCT_ORDER_PAYMENT_TIMEOUT, productOrder.getPaySn());
        jobInfo.setJobName(jobName);
        jobInfo.setFireDate(TypeConverterUtils.LocalDateTime2Date(productOrder.getLastPayTime()));
        // 不设置参数了，名字里面有详情
        quartzService.addJob(jobInfo);

        // 调用支付接口
        return payBillService.pay(bill);
    }

    private ProductOrderDetail initProductOrderDetail(ProductOrder order, Product product) {
        ProductOrderDetail detail = new ProductOrderDetail();
        // costprice, createtime, name, offsaletime, onsaletime, resources, salecount, saleprice, state
        BeanUtils.copyProperties(product, detail);
        detail.setSalecount(order.getPrice().divide(product.getSaleprice()).longValue()); // 订单总价格/商品单价
        detail.setProduct_id(product.getId());
        detail.setOrder_id(order.getId());
        return detail;
    }

    private PayBill initBill(Long payMethod, Product product, User user, ProductOrder order) {
        PayBill bill = new PayBill();
        bill.setDigest("[摘要]: " + product.getName() + "服务支付单！");
        bill.setMoney(order.getPrice());
        bill.setUnionPaySn(order.getPaySn());
        bill.setState(PayConstants.TO_BE_PAID);
        bill.setLastPayTime(order.getLastPayTime());
        bill.setPayChannel(payMethod);
        bill.setBusinessType(PayConstants.BUSINESS_TYPE_PRODUCT);
        bill.setBusinessKey(order.getId());
        bill.setUser_id(user.getId());
        bill.setShop_id(order.getShop_id());
        bill.setNickName(user.getUsername());
        return bill;
    }

    @Override
    public void cancelOrder(Long orderId) {

    }

    @Override
    public void cancelByQuartz(String paySn) {
        ProductOrder productOrder = productOrderMapper.loadByPaySn(paySn);
        if (productOrder.getState() != PayConstants.PAID) { // 确认不是已经支付了
            // 还要主动调支付宝查询接口查，保证幂等性
            productOrder.setState(PayConstants.CANCELLED);
            productOrderMapper.update(productOrder);
        }
    }

    @Override
    public PageList<ProductOrder> queryPage(ProductOrderQuery productOrderQuery, LoginInfo loginInfo) {
        if (loginInfo.getType() == LoginInfoConstants.ADMIN) {
            productOrderQuery.setShop_id(employeeMapper.loadByLoginInfoId(loginInfo.getId()).getShop_id());
        } else { //前台用户
            productOrderQuery.setUser_id(userMapper.loadByLoginInfoId(loginInfo.getId()).getId());
        }
        return super.queryPage(productOrderQuery);
    }

    @Override
    public ProductOrder findDetailById(Long id) {
        return productOrderMapper.loadDetailById(id);
    }

    private ProductOrder initProductOrder(Product product, User user, BigDecimal quantity) {
        ProductOrder order = new ProductOrder();
        order.setDigest(String.format("[摘要]: %s服务购买%s次的订单！", product.getName(), quantity.toString()));
        order.setState(OrderConstants.TO_BE_PAID);
        order.setPrice(product.getSaleprice().multiply(quantity)); // 乘数量
        String orderSn = CodeGenerateUtils.generateOrderSn(user.getId());
        order.setOrderSn(orderSn);
        // 方便后面支付单用
        order.setPaySn(CodeGenerateUtils.generateUnionPaySn());
        order.setLastPayTime(LocalDateTime.now().plusMinutes(orderTimeoutMinutes));
        order.setProduct_id(product.getId());
        order.setUser_id(user.getId());
        order.setShop_id(product.getShop_id());
        return order;
    }

    private OrderAddress initOrderAddress(ProductOrder order, UserAddress userAddress) {
        OrderAddress orderAddress = new OrderAddress();
        BeanUtils.copyProperties(userAddress, orderAddress);
        orderAddress.setOrder_id(order.getId());
        orderAddress.setOrderSn(order.getOrderSn());
        return orderAddress;
    }
}
