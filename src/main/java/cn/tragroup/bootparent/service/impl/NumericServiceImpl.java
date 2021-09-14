package cn.tragroup.bootparent.service.impl;

import cn.tragroup.bootparent.service.NumericService;
import com.baomidou.mybatisplus.core.conditions.AbstractLambdaWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.List;

/**
 * 序列的BaseService，默认的排序字段为 `number` <br/>
 * 可重写 getColName()修改排序字段
 *
 * @param <Mapper> DAO
 * @param <Entity> POJO
 * @author GengChuanqi
 * @create 2020-06-09 09:05
 */
public abstract class NumericServiceImpl<Mapper extends BaseMapper<Entity>, Entity>
        extends ServiceImpl<Mapper, Entity> implements NumericService<Entity> {

    @Override
    public boolean save(Entity entity) {
        return super.save(setNumber(entity, getLastNumber(entity) + 1));
    }

    @Override
    public boolean saveBatch(List<Entity> entityList) {
        if (CollectionUtils.isEmpty(entityList))
            throw new NullPointerException("entityList is null of empty");
        var lastNumber = getLastNumber(entityList.get(0));
        var i = 1;
        for (Entity entity : entityList) {
            setNumber(entity, lastNumber + i);
            i++;
        }
        return super.saveBatch(entityList);
    }

    @Override
    public boolean updateSequence(Object number, int count, boolean multi, boolean isAfter, boolean isAdd, Entity entity) {
        var wrapper = Wrappers.<Entity>update();
        wrapper.lt(multi && !isAfter, getNumberColName(), number)
                .gt(multi && isAfter, getNumberColName(), number)
                .eq(!multi, getNumberColName(), number)
                .setSql(MessageFormat.format("{0} = {0} {1} {2}", getNumberColName(), isAdd ? '+' : '-', count));
        return update(numberIntercept(wrapper, entity));
    }

    @Override
    public boolean removeById(Serializable id) {
        var entity = this.getById(id);
        var flag = super.removeById(id);
        //删除成功 将后面的序号全都 - 1
        if (flag) updateSequenceAfterSubMulti(SystemMetaObject.forObject(entity).getValue(getColNameTrim()), 1, entity);
        return flag;
    }

    @SuppressWarnings("unchecked")
    @SneakyThrows
    protected AbstractLambdaWrapper<Entity, ?> getLambdaWrapper(Wrapper<Entity> wrapper) {
        Class<?> clazz = Class.forName(wrapper.getClass().getTypeName());
        var lambda = clazz.getMethod("lambda").invoke(wrapper);
        if (lambda instanceof AbstractLambdaWrapper)
            return (AbstractLambdaWrapper<Entity, ?>) lambda;
        throw new RuntimeException("不是lambda类型的wrapper");
    }
}
