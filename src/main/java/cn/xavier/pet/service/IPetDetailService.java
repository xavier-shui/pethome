package cn.xavier.pet.service;

import cn.xavier.basic.service.IBaseService;
import cn.xavier.pet.domain.PetDetail;

public interface IPetDetailService extends IBaseService<PetDetail> {
    PetDetail getByPetId(Long petId);
}
