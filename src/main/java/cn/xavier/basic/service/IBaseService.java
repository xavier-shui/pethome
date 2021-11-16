package cn.xavier.basic.service;

import cn.xavier.basic.query.BaseQuery;
import cn.xavier.basic.util.PageList;

import java.util.List;

/**
 * 业务层公共接口
 *
 * @author Zheng-Wei Shui
 * @date 11/16/2021
 */
public interface IBaseService<T> {
    /**
     * Add * 加一个
     *
     * @param
     */
    void add(T t);

    /**
     * Update * 更新一个
     *
     * @param
     */
    void update(T t);

    /**
     * Remove * 删除一个
     *
     * @param
     */
    void remove(Long id);

    /**
     * Find by id 根据id找一个
     *
     * @param
     * @return
     */
    T findById(Long id);

    /**
     * Find all 找全部
     *
     * @return
     */
    List<T> findAll();

    /**
     * Query page 分页+高级查询
     *
     * @param
     * @return
     */
    PageList<T> queryPage(BaseQuery query);

    /**
     * Batch remove 批量删除
     *
     * @param
     */
    void batchRemove(List<Long> ids);
}
