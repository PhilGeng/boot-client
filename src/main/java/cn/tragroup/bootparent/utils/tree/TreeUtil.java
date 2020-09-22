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

    public static <Entity extends TreeModel<Integer, Entity>> List<Entity> makeTree(List<Entity> source) {
        return TreeUtil.makeTree(source, DEFAULT_INT_ROOT, (a, b) -> {
        });
    }

    public static <I, Entity extends TreeModel<I, Entity>> List<Entity> makeTree(List<Entity> source, I root) {
        return TreeUtil.makeTree(source, root, (a, b) -> {
        });
    }

    public static <I, Entity extends TreeModel<I, Entity>> List<Entity> makeTree(List<Entity> source, I root, BiConsumer<Map<I, Entity>, List<Entity>> callback) {
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
        callback.accept(map, res);
        return res;
    }


    public static <I, Entity extends TreeModel<Integer, Entity>> List<Entity> getAllEntity(List<Entity> source, Integer id) {
        return getAllEntity(source, id, DEFAULT_INT_ROOT);
    }

    public static <I, Entity extends TreeModel<I, Entity>> List<Entity> getAllEntity(List<Entity> source, I id, I root) {
        AtomicReference<Map<I, Entity>> map = new AtomicReference<>();
        makeTree(source, root, (m, list) -> map.set(m));
        return getAllEntity(map.get().get(id), new ArrayList<>());
    }

    public static <I, Entity extends TreeModel<I, Entity>> List<Entity> getAllEntity(Entity source) {
        return getAllEntity(source, new ArrayList<>());
    }

    private static <I, Entity extends TreeModel<I, Entity>> List<Entity> getAllEntity(Entity source, List<Entity> res) {
        List<Entity> children = source.getChildren();
        res.add(source);
        if (children != null && children.size() > 0) {
            children.forEach(child -> getAllEntity(child, res));
        }
        return res;
    }
}
