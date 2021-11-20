package cn.xavier.basic.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 实体类基类
 * @author Zheng-Wei Shui
 * @date 11/16/2021
 */
@Data
public class BaseDomain implements Serializable {
    private Long id;
}
