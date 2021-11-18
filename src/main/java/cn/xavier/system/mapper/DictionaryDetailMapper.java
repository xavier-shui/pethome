package cn.xavier.system.mapper;

import cn.xavier.basic.mapper.BaseMapper;
import cn.xavier.system.domain.DictionaryDetail;

import java.util.List;

/**
 * Dictionary detail mapper
 *
 * @author Zheng -Wei Shui
 * @date 11 /18/2021
 */
public interface DictionaryDetailMapper extends BaseMapper<DictionaryDetail> {

    /**
     * Remove by type ids * 根据类型id批量删除
     *
     * @param typeIds type ids
     */
    void removeByTypeIds(List<Long> typeIds);

    /**
     * Load by typeId * 根据类型id查找
     *
     * @param
     * @return
     */
    List<DictionaryDetail> loadByTypeId(Long typeId);
}
