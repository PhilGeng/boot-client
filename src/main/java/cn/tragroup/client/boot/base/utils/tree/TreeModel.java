package cn.tragroup.client.boot.base.utils.tree;

import java.util.List;

/**
 * @author 耿传奇
 * @create 2020-07-09 09:26
 */
public interface TreeModel<I ,T extends TreeModel<I, T>> {

    I getParentId();

    I getId();

    List<T> getChildren();

    void setChildren(List<T> list);
}
