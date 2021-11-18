package cn.xavier.system.service;

import cn.xavier.basic.service.IBaseService;
import cn.xavier.system.domain.DictionaryDetail;

import java.util.List;

/**
 * @author Zheng-Wei Shui
 * @date 11/18/2021
 */
public interface IDictionaryDetailService extends IBaseService<DictionaryDetail> {

    /**
     * Find by typeId 根据类型id查
     *
     * @param
     * @return
     */
    List<DictionaryDetail> findByTypeId(Long typeId);
}
