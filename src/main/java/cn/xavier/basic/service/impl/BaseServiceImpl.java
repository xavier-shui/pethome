package cn.xavier.basic.service.impl;

import cn.xavier.basic.mapper.BaseMapper;
import cn.xavier.basic.query.BaseQuery;
import cn.xavier.basic.service.IBaseService;
import cn.xavier.basic.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zheng-Wei Shui
 * @date 11/16/2021
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public class BaseServiceImpl<T> implements IBaseService<T> {

    @Autowired // 可将容器中子类型的对象注入
    protected BaseMapper<T> mapper;

    @Override
    @Transactional
    public void add(T t) {
        mapper.save(t);
    }

    @Override
    @Transactional
    public void update(T t) {
        mapper.update(t);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        mapper.delete(id);
    }

    @Override
    public T findById(Long id) {
        return mapper.loadById(id);
    }

    @Override
    public List<T> findAll() {
        return mapper.loadAll();
    }

    @Override
    public PageList<T> queryPage(BaseQuery query) {
        Long totals = mapper.queryCount(query);
        // 提高查库效率
        if (totals == 0) {
            return new PageList<>();
        }
        List<T> rows = mapper.queryData(query);
        return new PageList<>(totals, rows);
    }

    @Override
    @Transactional
    public void batchRemove(List<Long> ids) {
        mapper.batchDelete(ids);
    }
}
