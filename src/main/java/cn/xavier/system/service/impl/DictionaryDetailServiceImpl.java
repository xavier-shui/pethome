package cn.xavier.system.service.impl;

import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.system.domain.DictionaryDetail;
import cn.xavier.system.mapper.DictionaryDetailMapper;
import cn.xavier.system.service.IDictionaryDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zheng-Wei Shui
 * @date 11/18/2021
 */
@Service
public class DictionaryDetailServiceImpl extends BaseServiceImpl<DictionaryDetail> implements IDictionaryDetailService {

    @Override
    public List<DictionaryDetail> findByTypeId(Long typeId) {
        return ((DictionaryDetailMapper)mapper).loadByTypeId(typeId);
    }
}
