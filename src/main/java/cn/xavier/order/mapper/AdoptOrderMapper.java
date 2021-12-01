package cn.xavier.order.mapper;

import cn.xavier.basic.mapper.BaseMapper;
import cn.xavier.order.domain.AdoptOrder;

public interface AdoptOrderMapper extends BaseMapper<AdoptOrder> {
    /**
     * Load detail by id 查详情
     *
     * @param id id
     * @return the adopt order
     */
    AdoptOrder loadDetailById(Long id);
}
