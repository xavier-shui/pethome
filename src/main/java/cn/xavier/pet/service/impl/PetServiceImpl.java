package cn.xavier.pet.service.impl;

import cn.xavier.basic.service.impl.BaseServiceImpl;
import cn.xavier.basic.constant.MarketStateConstants;
import cn.xavier.pet.domain.Pet;
import cn.xavier.pet.domain.PetDetail;
import cn.xavier.pet.mapper.PetDetailMapper;
import cn.xavier.pet.mapper.PetMapper;
import cn.xavier.pet.service.IPetService;
import cn.xavier.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PetServiceImpl extends BaseServiceImpl<Pet> implements IPetService {

    @Autowired
    private PetDetailMapper petDetailMapper;

    @Autowired
    private PetMapper petMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void add(Pet pet) {
        // 先保存获取自增ID
        super.add(pet);
        // 获取前端传回详情
        PetDetail detail = pet.getDetail();
        // 关联好再保存
        // 前端不填详情，detail是前端设置的默认的空字符串。 如果前端会传null，这里要判断
        detail.setPet_id(pet.getId());
        petDetailMapper.save(detail);
    }

    @Override
    @Transactional
    public void batchOnOrOffTheMarket(List<Long> ids, int state) {
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        params.put("state", state);
        params.put("date", new Date());
        // 多个参数可以用map或@Param
        petMapper.batchOnOrOffTheMarket(params);
    }

    @Override
    public Pet findDetailById(Long id) {
        return petMapper.loadDetailById(id);
    }

    @Override
    @Transactional
    public void adopt(Long petId, Long userId) {
        Pet pet = petMapper.loadById(petId);
        // 修改状态为下架
        pet.setState(MarketStateConstants.OFF_THE_MARKET);

        // 下架时间
        pet.setOffsaletime(new Date());

        // 设置user_id, 被领养还需设置店铺id为null
        pet.setUser_id(userId);
        pet.setShop_id(null);
        petMapper.update(pet);
    }
}
