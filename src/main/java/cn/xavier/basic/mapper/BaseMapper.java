package cn.xavier.basic.mapper;

import cn.xavier.basic.query.BaseQuery;

import java.util.List;

/**
 * Mapper基类
 * @author Zheng-Wei Shui
 * @date 11/16/2021
 */
public interface BaseMapper<T> {

    /**
     * Save * 保存一个
     *
     * @param
     */
    void save(T t);

    /**
     * Update * 更新一个
     *
     * @param
     */
    void update(T t);

    /**
     * Delete * 删除一个
     *
     * @param id
     */
    void delete(Long id);

    /**
     * Load by id 查询一个
     *
     * @param id
     * @return
     */
    T loadById(Long id);

    /**
     * Load all 查全部
     *
     * @return
     */
    List<T> loadAll();

    /**
     * Query count 高级+分页查询 总数
     *
     * @param
     * @return
     */
    Long queryCount(BaseQuery query);

    /**
     * Query data 高级+分页查询 数据
     *
     * @param
     * @return
     */
    List<T> queryData(BaseQuery query);

    /**
     * 批量删除
     *
     * @param ids
     */
    void batchDelete(List<Long> ids);
}
