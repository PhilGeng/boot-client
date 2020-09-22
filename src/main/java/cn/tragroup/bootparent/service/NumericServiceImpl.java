package cn.tragroup.bootparent.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

/**
 *
 * 序列的BaseService，默认的排序字段为 `number` <br/>
 * 可重写 getColName()修改排序字段
 *
 * @author GengChuanqi
 * @create 2020-06-09 09:05
 * @param <Mapper> DAO
 * @param <Entity> POJO
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
    public boolean updateSequence(Object number, int count, boolean multi, boolean isAfter, boolean isAdd) {
        return update(new UpdateWrapper<Entity>()
                .lt(multi && !isAfter, getColName(), number)
                .gt(multi && isAfter, getColName(), number)
                .eq(!multi, getColName(), number)
                .setSql(MessageFormat.format("{0} = {0} {1} {2}", getColName(), isAdd ? '+' : '-', count))
        );
    }

    @Override
    public boolean removeById(Serializable id) {
        var entity = this.getById(id);
        var flag = super.removeById(id);
        //删除成功 将后面的序号全都 - 1
        if (flag) updateSequenceAfterSubMulti(getMetaObject(entity).getValue(getColNameTrim()),  1);
        return flag;
    }

    /**
     * 获取最后一个序号
     *
     * @param entity insert时的对象
     */
    @Override
    public int getLastNumber(Entity entity) {
        var wrapper = new QueryWrapper<Entity>().select("max(" + getColName() + ") max");
        var map = getMap(numberIntercept(wrapper, entity));
        if (map != null) {
            var temp = map.get("max");
            if (getType(entity) == Integer.class) return (int) temp;
            else return ((Long) temp).intValue();
        }
        return 0;
    }

    /**
     * 获取最后一个序号时 自定义条件拦截
     *
     * @param wrapper 条件
     * @param entity  insert时的对象
     * @return wrapper
     */
    @Override
    public QueryWrapper<Entity> numberIntercept(QueryWrapper<Entity> wrapper, Entity entity) {
        return wrapper;
    }

    /**
     * 排序字段名
     *
     * @return 序号字段名
     */
    public String getColName() {
        return "`number`";
    }

    /**
     * 排序字段名
     *
     * @return 序号字段名
     */
    public String getColNameTrim() {
        return getColName().replace("`", "");
    }

    /**
     * 反射设置序号值
     *
     * @param entity insert时的对象
     * @return 返回设置完序号之后的对象
     */
    protected Entity setNumber(Entity entity, int number) {
        var filedName = getColNameTrim();
        var meta = getMetaObject(entity);
        //获取字段类型
        var type = meta.getGetterType(getColNameTrim());

        //赋值
        if (type == Integer.class) {
            getMetaObject(entity).setValue(filedName, number);
        } else {
            getMetaObject(entity).setValue(filedName, (long) number);
        }
        return entity;
    }

    //#region

    private Class<?> getType(Entity object) {
        return getMetaObject(object).getGetterType(getColNameTrim());
    }

    private MetaObject getMetaObject(Entity object) {
        return SystemMetaObject.forObject(object);
    }
    //#endregion

}
