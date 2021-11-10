package cn.xavier.basic.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zheng-Wei Shui
 * @date 11/11/2021
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageList<T> {
    // 总条数
    private Long totals = 0L;
    // 当前页数据
    private List<T> rows = new ArrayList<>();
}
