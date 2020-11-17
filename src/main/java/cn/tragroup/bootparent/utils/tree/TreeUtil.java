package cn.tragroup.bootparent.utils.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

/**
 * @author 耿传奇
 * @create 2020-07-09 09:26
 */
public class TreeUtil {

    private static final Integer DEFAULT_INT_ROOT = -1;

    public static <Entity extends TreeModel<Integer, Entity>> List<Entity> makeTreeList(List<Entity> source) {
        return TreeUtil.makeTreeList(source, DEFAULT_INT_ROOT);
    }

    public static <I, Entity extends TreeModel<I, Entity>> List<Entity> makeTreeList(List<Entity> source, I root) {
        return TreeUtil.makeTreeResult(source, root).getList();
    }

    public static <I, Entity extends TreeModel<I, Entity>> TreeResult<I, Entity> makeTreeResult(List<Entity> source, I root) {
        List<Entity> res = new ArrayList<>();
        Map<I, Entity> map = new HashMap<>();
        source.forEach(item -> {
            map.put(item.getId(), item);
            var parentId = item.getParentId();
            if (!parentId.equals(root)) {
                var entity = map.get(parentId);
                if (entity != null) {
                    List<Entity> list = entity.getChildren();
                    if (entity.getChildren() == null) {
                        list = new ArrayList<>();
                        entity.setChildren(list);
                    }
                    list.add(item);
                }
            } else res.add(item);
        });
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
}
