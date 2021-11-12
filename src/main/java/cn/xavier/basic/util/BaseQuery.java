package cn.xavier.basic.util;

import lombok.Data;

/**
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
@Data
public class BaseQuery {
    // 当前页
    private Integer currentPage = 1;
    // 每页条数
    private Integer pageSize = 5;

    public Integer getStart() {
        // 没加this也行
        return  (currentPage - 1) * pageSize;
    }
}
