package cn.xavier.system.query;

import cn.xavier.basic.query.BaseQuery;
import lombok.Data;

/**
 * @author Zheng-Wei Shui
 * @date 11/18/2021
 */
@Data
public class DictionaryDetailQuery extends BaseQuery {
    // 类型名字
    private String typeName;
}
