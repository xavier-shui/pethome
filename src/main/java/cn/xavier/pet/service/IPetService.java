package cn.xavier.pet.service;

import cn.xavier.basic.domain.LoginInfo;
import cn.xavier.basic.service.IBaseService;
import cn.xavier.pet.domain.Pet;

import java.util.Date;
import java.util.List;

public interface IPetService extends IBaseService<Pet> {

    /**
     * 宠物批量上下架
     *
     * @param ids              ids
     * @param state on or off the market
     * @param date             date
     */
    void batchOnOrOffTheMarket(List<Long> ids, int state, Date date);

    /**
     * Find detail by id 宠物详情页面
     *
     * @param id id
     * @return the pet
     */
    Pet findDetailById(Long id);

    /**
     * Adopt * 用户领养宠物
     *
     * @param id        id
     * @param loginInfo login info
     */
    void adopt(Long id, LoginInfo loginInfo);
}
