package cn.xavier.pet.domain;

import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;

@Data
public class PetDetail extends BaseDomain {
    private Long pet_id;
    private String adoptNotice;
    private String intro;
}
