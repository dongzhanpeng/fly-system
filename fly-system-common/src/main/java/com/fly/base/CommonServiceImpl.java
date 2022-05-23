package com.fly.base;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

/**
 * <p>
 * CommonServiceImpl
 * </p>
 *
 * @author Fly
 * @since 2022-05-19
 */
@Slf4j
public class CommonServiceImpl<M extends CommonMapper<T>, T> extends ServiceImpl<M, T> implements ICommonService<T> {

    @Override
    public boolean removeById(T entity) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        return this.removeById((Serializable) tableInfo.getPropertyValue(entity, tableInfo.getKeyProperty()));
    }

    @Override
    public boolean removeById(Serializable id) {
        return this.removeById(id, true);
    }

    @Override
    public boolean removeByIds(Collection<?> list) {
        return this.removeBatchByIds(list, DEFAULT_BATCH_SIZE, true);
    }

    @Override
    public boolean removeBatchByIds(Collection<?> list, int batchSize) {
        return this.removeBatchByIds(list, batchSize, true);
    }


    @Override
    public boolean remove(Wrapper<T> queryWrapper) {
        throw ExceptionUtils.mpe("can not use this method for \"%s\"", "remove(queryWrapper)");
    }

    @Override
    public boolean removeByIds(Collection<?> list, boolean useFill) {
        return this.removeBatchByIds(list, DEFAULT_BATCH_SIZE, true);
    }

    @Override
    public boolean removeBatchByIds(Collection<?> list) {
        return this.removeBatchByIds(list, DEFAULT_BATCH_SIZE, true);
    }

    @Override
    public boolean removeBatchByIds(Collection<?> list, boolean useFill) {
        return this.removeBatchByIds(list, DEFAULT_BATCH_SIZE, true);
    }

    private String fieldToColumn(String field) {
        return Optional.ofNullable(LambdaUtils.getColumnMap(entityClass).get(field.toUpperCase())).map(z -> z.getColumn()).orElse("");
    }

    @Override
    public boolean saveBatch(Collection<T> entityList) {
        return super.saveBatch(entityList);
    }
}
