package cn.xavier.pet.domain;

import cn.xavier.basic.domain.BaseDomain;
import cn.xavier.org.domain.Shop;
import cn.xavier.basic.constant.MarketStateConstants;
import cn.xavier.user.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Pet extends BaseDomain {
    private String name;
    private String resources;
    private BigDecimal saleprice;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date offsaletime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date onsaletime;
    private Integer state = MarketStateConstants.OFF_THE_MARKET;
    private BigDecimal costprice;
    private Date createtime = new Date();
    private Long type_id;
    private PetType type;
    private Long shop_id;
    private Shop shop;
    private Long user_id;
    private User user;
    private Long search_master_msg_id;

    private Integer payType;

    private PetDetail detail = new PetDetail();

}
