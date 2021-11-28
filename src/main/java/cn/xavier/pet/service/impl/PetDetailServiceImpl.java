package cn.xavier.pet.service.impl;

import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.pet.domain.PetDetail;
import cn.xavier.pet.mapper.PetDetailMapper;
import cn.xavier.pet.service.IPetDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetDetailServiceImpl extends BaseServiceImpl<PetDetail> implements IPetDetailService {
    @Autowired
    private PetDetailMapper petDetailMapper;

    @Override
    public PetDetail getByPetId(Long petId) {
        return petDetailMapper.loadByPetId(petId);
    }
}
