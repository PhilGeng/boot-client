package cn.tragroup.bootparent.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.util.CollectionUtils;

import java.util.List;

@SuppressWarnings("UnusedReturnValue")
public interface NumericService<Entity> extends IService<Entity> {

    boolean saveBatch(List<Entity> entityList);

    /**
     * 获取最后一个序号
     *
     * @param entity insert时的对象
     */
    default int getLastNumber(Entity entity) {
        var wrapper = new QueryWrapper<Entity>().select("max(" + getNumberColName() + ") max");
        var map = getMap(numberIntercept(wrapper, entity));
        if (map != null) {
            var temp = map.get("max");
            if (SystemMetaObject.forObject(entity).getGetterType(getColNameTrim()) == Integer.class) return (int) temp;
            else return ((Long) temp).intValue();
        }
        return 0;
    }

    /**
     * 排序字段名
     *
     * @return 序号字段名
     */
    default String getColNameTrim() {
        return getNumberColName().replace("`", "");
    }

    /**
     * 进行更新操作时 自定义条件拦截
     *
     * @param wrapper 条件
     * @param entity  insert时的对象
     * @return wrapper
     */
    default Wrapper<Entity> numberIntercept(Wrapper<Entity> wrapper, Entity entity) {
        return wrapper;
    }

    /**
     * 排序字段名
     *
     * @return 序号字段名
     */
    default String getNumberColName() {
        return "`number`";
    }


    /**
     * 反射设置序号值
     *
     * @param entity insert时的对象
     * @return 返回设置完序号之后的对象
     */
    default Entity setNumber(Entity entity, int number) {
        var filedName = getColNameTrim();
        var meta = SystemMetaObject.forObject(entity);
        //获取字段类型
        var type = meta.getGetterType(getColNameTrim());

        //赋值
        if (type == Integer.class) {
            meta.setValue(filedName, number);
        } else {
            meta.setValue(filedName, (long) number);
        }
        return entity;
    }


    /**
     * 批量更新序列: <br/>
     * 如果 {@code multi} 为 {@code true} 并且 {@code isAdd} 为 {@code true} 那么将会更新的内容为 <br/>
     * SET column + {@code count} where column < {@code number} <br/>
     * 如果 {@code multi} 为 {@code false} 并且 {@code isAdd} 为 {@code false} 那么将会更新的内容为 <br/>
     * SET column - {@code count} where column = {@code number} <br/>
     *
     * @param number  要更新的序列起始值
     * @param count   自增或者自减的值
     * @param multi   是否更新多个
     * @param isAdd   是否增加
     * @param isAfter 是否更新后面的序列
     * @return 更新条数 >= 1 则返回 {@code true}
     */
    boolean updateSequence(Object number, int count, boolean multi, boolean isAfter, boolean isAdd , Entity entity);

    default boolean updateSequenceAfterAddOnly(Object number, int count, Entity entity) {
        return updateSequence(number, count, false, true, true, entity);
    }

    default boolean updateSequenceAfterAddMulti(Object number, int count, Entity entity) {
        return updateSequence(number, count, true, true, true, entity);
    }

    default boolean updateSequenceAfterSubOnly(Object number, int count, Entity entity) {
        return updateSequence(number, count, false, true, false, entity);
    }

    default boolean updateSequenceAfterSubMulti(Object number, int count, Entity entity) {
        return updateSequence(number, count, true, true, false, entity);
    }

    default boolean updateSequenceBeforeAddOnly(Object number, int count, Entity entity) {
        return updateSequence(number, count, false, false, true, entity);
    }

    default boolean updateSequenceBeforeAddMulti(Object number, int count, Entity entity) {
        return updateSequence(number, count, true, false, true, entity);
    }

    default boolean updateSequenceBeforeSubOnly(Object number, int count, Entity entity) {
        return updateSequence(number, count, false, false, false, entity);
    }

    default boolean updateSequenceBeforeSubMulti(Object number, int count, Entity entity) {
        return updateSequence(number, count, true, false, false, entity);
    }
}
