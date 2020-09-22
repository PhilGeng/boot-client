package cn.tragroup.bootparent.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.List;

public interface NumericService<Entity> {

    boolean saveBatch(List<Entity> entityList);

    int getLastNumber(Entity entity);

    QueryWrapper<Entity> numberIntercept(QueryWrapper<Entity> wrapper, Entity entity);

    String getColName();

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
    boolean updateSequence(Object number, int count, boolean multi, boolean isAfter, boolean isAdd);

    default boolean updateSequenceAfterAddOnly(Object number, int count) {
        return updateSequence(number, count, false, true, true);
    }

    default boolean updateSequenceAfterAddMulti(Object number, int count) {
        return updateSequence(number, count, true, true, true);
    }

    default boolean updateSequenceAfterSubOnly(Object number, int count) {
        return updateSequence(number, count, false, true, false);
    }

    default boolean updateSequenceAfterSubMulti(Object number, int count) {
        return updateSequence(number, count, true, true, false);
    }

    default boolean updateSequenceBeforeAddOnly(Object number, int count) {
        return updateSequence(number, count, false, false, true);
    }

    default boolean updateSequenceBeforeAddMulti(Object number, int count) {
        return updateSequence(number, count, true, false, true);
    }

    default boolean updateSequenceBeforeSubOnly(Object number, int count) {
        return updateSequence(number, count, false, false, false);
    }

    default boolean updateSequenceBeforeSubMulti(Object number, int count) {
        return updateSequence(number, count, true, false, false);
    }
}
