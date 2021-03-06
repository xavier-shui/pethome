package cn.xavier.basic.query;

import lombok.Data;

/**
 * 封装查询对象基类
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
@Data
public class BaseQuery {
    // 当前页
    private Integer currentPage = 1;
    // 每页条数
    private Integer pageSize = 5;
    // 关键字查询
    private String keyword;

    public Integer getStart() {
        // 没加this也行
        return  (currentPage - 1) * pageSize;
    }
}
