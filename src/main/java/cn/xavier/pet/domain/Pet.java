package cn.xavier.pet.domain;

import cn.xavier.basic.domain.BaseDomain;
import cn.xavier.org.domain.Shop;
import cn.xavier.user.domain.User;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Pet extends BaseDomain {
    private String name;
    private String resources;
    private BigDecimal saleprice;
    private Date offsaletime;
    private Date onsaletime;
    private Integer state;
    private BigDecimal costprice;
    private Date createtime;
    private Long type_id;
    private PetType type;
    private Long shop_id;
    private Shop shop;
    private Long user_id;
    private User user;
    private Long adopt_id;

    private PetDetail detail = new PetDetail();

}
