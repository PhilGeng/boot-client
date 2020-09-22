package cn.tragroup.client.boot.base.utils.query;

import cn.tragroup.client.boot.base.utils.query.model.QueryEntity;
import cn.tragroup.client.boot.base.utils.query.model.QueryType;
import cn.tragroup.client.boot.base.utils.query.model.Query;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.CaseFormat;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author 耿传奇
 * @create 2020-07-04 16:03
 */
public class QueryUtils {

    public static final Class<Query> QUERY = Query.class;
    public static final Class<TableField> FIELD = TableField.class;

    public static boolean hasQueryAnnotation(Object o) {
        var fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(QUERY)) return true;
        }
        return false;
    }

    public static Collection<QueryEntity> getQueryFields(Object o) throws IllegalAccessException {
        var fields = o.getClass().getDeclaredFields();
        List<QueryEntity> list = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(QUERY)) {
                // 如果有 TableField 注解就取 TableField 中的值为列名，否则将字段驼峰转下划线
                var column = field.isAnnotationPresent(FIELD)
                        ? field.getAnnotation(FIELD).value()
                        : CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
                var type = field.getAnnotation(QUERY).value();
                list.add(new QueryEntity(column, type, field.get(o)));
            }
        }
        return list;
    }

    public static <T> QueryWrapper<T> getQueryWrapper(T entity) throws IllegalAccessException {
        var list = getQueryFields(entity);
        if (list.isEmpty()) throw new RuntimeException("不支持该搜索，请确定你使用了 Query 注解");
        var wrapper = new QueryWrapper<T>();
        list.forEach(queryEntity -> {
            var value = queryEntity.getValue();
            if (value != null) {
                var type = queryEntity.getType();
                var column = queryEntity.getColumn();
                wrapper.eq(type == QueryType.DEFAULT, column, value)
                        .like(type == QueryType.LIKE, column, value)
                        .lt(type == QueryType.LT, column, value)
                        .gt(type == QueryType.GT, column, value)
                        .le(type == QueryType.LE, column, value)
                        .ge(type == QueryType.GE, column, value);
            }
        });
        return wrapper;
    }
}
