package cn.tragroup.bootparent.utils.tree;

import java.util.List;

/**
 * 树模型
 * @author 耿传奇
 * @create 2020-07-09 09:26
 * @param <I> 主键的类型
 * @param <T> 子类的类型
 */
public interface TreeModel<I ,T extends TreeModel<I, T>> {

    I getParentId();

    I getId();

    List<T> getChildren();

    void setChildren(List<T> list);
}
