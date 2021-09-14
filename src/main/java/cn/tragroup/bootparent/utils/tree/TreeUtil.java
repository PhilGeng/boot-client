package cn.tragroup.bootparent.utils.tree;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 耿传奇
 * @create 2020-07-09 09:26
 */
@Slf4j
public class TreeUtil {

    /**
     * 默认的int型的顶级父ID
     */
    private static final Integer DEFAULT_INT_ROOT = -1;

    public static <Entity extends TreeModel<Integer, Entity>> List<Entity> makeTreeList(List<Entity> source) {
        return TreeUtil.makeTreeList(source, DEFAULT_INT_ROOT);
    }

    public static <I, Entity extends TreeModel<I, Entity>> List<Entity> makeTreeList(List<Entity> source, I root) {
        return TreeUtil.makeTreeResult(source, root).getList();
    }

    /**
     * 生成结果
     *
     * @param source   源数据
     * @param root     顶级父ID
     * @param <I>      父ID的类型
     * @param <Entity> 每一个元素的类型
     * @return 格式化后的结果
     */
    public static <I, Entity extends TreeModel<I, Entity>> TreeResult<I, Entity> makeTreeResult(List<Entity> source, I root) {
        List<Entity> res = new ArrayList<>();
        List<Entity> tempList = new ArrayList<>();
        Map<I, Entity> map = new HashMap<>();
        // 遍历源数据
        for (Entity item : source) {// 将所有数据放入Map中
            map.put(item.getId(), item);
            var parentId = item.getParentId();
            if (!parentId.equals(root)) {
                var entity = map.get(parentId);
                // 找到父对象
                if (entity != null) {
                    setChildrenValue(entity,item);
                }else{
                    // 没找到父对象可能此时顺序问题，先缓存起来
                    tempList.add(item);
                }
            } else {
                // 顶级数据 ， 直接放入跟级
                res.add(item);
            }
        }
        for (Entity item : tempList) {
            var parent = map.get(item.getParentId());
            if(parent != null){
                setChildrenValue(parent,item);
            }else{
                log.error("id:[{}],parentId:[{}]未找到对应的父类。",item.getId(),item.getParentId());
            }
        }
        return new TreeResult<>(res, map);
    }

    public static <I, Entity extends TreeModel<Integer, Entity>> List<Entity> getAllEntity(List<Entity> source, Integer id) {
        return TreeUtil.getAllEntity(source, id, DEFAULT_INT_ROOT);
    }

    public static <I, Entity extends TreeModel<I, Entity>> List<Entity> getAllEntity(List<Entity> source, I id, I root) {
        var map = makeTreeResult(source, root).getMapping();
        return TreeUtil.getAllEntity(map.get(id), new ArrayList<>());
    }

    public static <I, Entity extends TreeModel<I, Entity>> List<Entity> getAllEntity(Entity source) {
        return TreeUtil.getAllEntity(source, new ArrayList<>());
    }

    /**
     * 将树形结构改为同级
     *
     * @param source   树形对象
     * @param res      导出的数组
     * @param <I>      父ID类型
     * @param <Entity> 树形类型
     * @return 结果
     */
    private static <I, Entity extends TreeModel<I, Entity>> List<Entity> getAllEntity(Entity source, List<Entity> res) {
        List<Entity> children = source.getChildren();
        res.add(source);
        if (children != null && children.size() > 0) {
            for (Entity child : children) {
                TreeUtil.getAllEntity(child, res);
            }
        }
        return res;
    }


    private static <I, Entity extends TreeModel<I, Entity>> void setChildrenValue(Entity parent, Entity children){
        var list = parent.getChildren();
        if (list == null) {
            list = new ArrayList<>();
            parent.setChildren(list);
        }
        list.add(children);
    }

}
