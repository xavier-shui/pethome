package cn.xavier.system.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Zheng-Wei Shui
 * @date 11/18/2021
 */
@Data
public class DictionaryType {
    private Long id;
    private String sn;
    private String name;
    private List<DictionaryDetail> details = new ArrayList<>();
}
