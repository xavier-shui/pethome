package cn.xavier.pet.query;

import cn.xavier.basic.query.BaseQuery;
import lombok.Data;

@Data
public class SearchMasterMsgQuery extends BaseQuery {
    private Integer state; // 前台户是null，查全部状态

    private Long user_id;

    private Long shop_id;
}
