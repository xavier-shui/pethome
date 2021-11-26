package cn.xavier.pet.service.impl;

import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.pet.domain.Pet;
import cn.xavier.pet.mapper.PetDetailMapper;
import cn.xavier.pet.mapper.PetMapper;
import cn.xavier.pet.service.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl extends BaseServiceImpl<Pet> implements IPetService {

    @Autowired
    private PetMapper petMapper;
    @Autowired
    private PetDetailMapper petDetailMapper;

}
