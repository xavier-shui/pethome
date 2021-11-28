package cn.xavier.pet.service;

import cn.xavier.basic.domain.LoginInfo;
import cn.xavier.basic.service.IBaseService;
import cn.xavier.basic.util.PageList;
import cn.xavier.pet.domain.Pet;
import cn.xavier.pet.domain.SearchMasterMsg;
import cn.xavier.pet.query.SearchMasterMsgQuery;

public interface ISearchMasterMsgService extends IBaseService<SearchMasterMsg> {

    /**
     * Publish * 用户发布寻主信息
     *
     * @param searchMasterMsg search master msg
     * @param loginInfo       login info
     */
    void publish(SearchMasterMsg searchMasterMsg, LoginInfo loginInfo);

    /**
     * List page list
     * 前后台查询寻主信息分页列表
     *
     * @param query     query
     * @param loginInfo login info
     * @return the page list
     */
    PageList<SearchMasterMsg> list(SearchMasterMsgQuery query, LoginInfo loginInfo);

    /**
     *  后台处理寻主消息
     *
     * @param pet pet
     */
    void handle(Pet pet);
}
