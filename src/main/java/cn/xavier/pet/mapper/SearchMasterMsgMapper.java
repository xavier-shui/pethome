package cn.xavier.pet.mapper;

import cn.xavier.basic.mapper.BaseMapper;
import cn.xavier.pet.domain.SearchMasterMsg;

public interface SearchMasterMsgMapper extends BaseMapper<SearchMasterMsg> {
    /**
     * Update state to processed by id *
     *
     * @param search_master_msg_id search master msg id
     */
    void updateState2ProcessedById(Long search_master_msg_id);
}
