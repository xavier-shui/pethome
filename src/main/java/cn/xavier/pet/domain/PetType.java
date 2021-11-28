package cn.xavier.pet.domain;

import cn.xavier.basic.domain.BaseDomain;
import lombok.Data;

@Data
public class PetType extends BaseDomain {
    private String name;
    private String description;
    private Long pid;

}
