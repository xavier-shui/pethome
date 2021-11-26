package cn.xavier.pet.mapper;

import cn.xavier.basic.mapper.BaseMapper;
import cn.xavier.pet.domain.Pet;

import java.util.Map;

public interface PetMapper extends BaseMapper<Pet> {
    void onsale(Map<String, Object> params);

    void offSale(Map<String, Object> params);

    Pet loadByIdDetail(Long id);
}
