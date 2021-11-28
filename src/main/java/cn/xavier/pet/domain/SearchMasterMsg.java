package cn.xavier.pet.domain;

import cn.xavier.pet.constant.SearchMasterMsgStateConstants;
import cn.xavier.basic.domain.BaseDomain;
import cn.xavier.org.domain.Shop;
import cn.xavier.user.domain.User;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SearchMasterMsg extends BaseDomain {
    private String name;
    private BigDecimal price;
    private Integer age;
    private Integer gender;
    private String coat_color;
    private String resources;
    private Long pet_type;
    private PetType type;
    private String address;
    private String title;
    private Integer state = SearchMasterMsgStateConstants.PENDING;
    private Long user_id;
    private User user;
    private Long shop_id;
    private Shop shop;

}
