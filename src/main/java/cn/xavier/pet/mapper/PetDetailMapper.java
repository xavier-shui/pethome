package cn.xavier.pet.mapper;

import cn.xavier.basic.mapper.BaseMapper;
import cn.xavier.pet.domain.PetDetail;

import java.io.Serializable;

public interface PetDetailMapper extends BaseMapper<PetDetail> {
    PetDetail loadByPetId(Long productId);

    void removeByPetId(Serializable id);

    void updateByPetId(PetDetail detail);
}
