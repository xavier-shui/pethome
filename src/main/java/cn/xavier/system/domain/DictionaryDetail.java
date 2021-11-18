package cn.xavier.system.domain;

import lombok.Data;

/**
 * @author Zheng-Wei Shui
 * @date 11/18/2021
 */
@Data
public class DictionaryDetail {
    private Long id;
    private String name;
    private Long types_id;
    private DictionaryType type;
}
