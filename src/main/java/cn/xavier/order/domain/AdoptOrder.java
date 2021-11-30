package cn.xavier.order.domain;

import cn.xavier.basic.domain.BaseDomain;
import cn.xavier.org.domain.Shop;
import cn.xavier.pet.domain.Pet;
import cn.xavier.user.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AdoptOrder extends BaseDomain {
    private String digest;
    private Integer state = 0;
    private BigDecimal price;
    private String orderSn;
    private String paySn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastPayTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date lastConfirmTime;
    private Long pet_id;
    private Long user_id;
    private Long shop_id;
    private Pet pet;
    private User user;
    private Shop shop;

}
