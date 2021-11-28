package cn.xavier.pet.mapper;

import cn.xavier.basic.mapper.BaseMapper;
import cn.xavier.pet.domain.Pet;

import java.util.Map;

public interface PetMapper extends BaseMapper<Pet> {

    Pet loadDetailById(Long id);

    /**
     * Batch on or off the market
     *
     * @param params params
     */
    void batchOnOrOffTheMarket(Map<String, Object> params);
}
