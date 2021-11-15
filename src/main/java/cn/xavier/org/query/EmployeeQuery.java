package cn.xavier.org.query;

import cn.xavier.basic.util.BaseQuery;
import lombok.Data;

/**
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
@Data
public class EmployeeQuery extends BaseQuery {
    // 关键字查询
    private String keyword;
}
