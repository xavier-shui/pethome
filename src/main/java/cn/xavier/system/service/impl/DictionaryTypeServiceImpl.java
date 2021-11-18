package cn.xavier.system.service.impl;

import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.system.domain.DictionaryType;
import cn.xavier.system.mapper.DictionaryDetailMapper;
import cn.xavier.system.service.IDictionaryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Zheng-Wei Shui
 * @date 11/18/2021
 */
@Service
public class DictionaryTypeServiceImpl extends BaseServiceImpl<DictionaryType> implements IDictionaryTypeService {

    @Autowired
    private DictionaryDetailMapper dictionaryDetailMapper;

    @Override
    public void remove(Long id) {
        // 删明细, id转单元素List
        dictionaryDetailMapper.removeByTypeIds(Collections.singletonList(id));

        // 删类型
        super.remove(id);
    }

    @Override
    public void batchRemove(List<Long> ids) {
        // 删明细
        dictionaryDetailMapper.removeByTypeIds(ids);

        // 删类型
        super.batchRemove(ids);
    }
}
