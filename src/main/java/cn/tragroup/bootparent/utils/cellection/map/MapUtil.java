package cn.tragroup.bootparent.utils.cellection.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@SuppressWarnings({"rawtypes", "CollectionAddAllCanBeReplacedWithConstructor"})
public class MapUtil {

    @SuppressWarnings("unchecked")
    public static <T> T get(Map map, String params) {
        var arr = params.split("\\.");
        var list = new ArrayList<String>();
        list.addAll(Arrays.asList(arr));
        var value = map.get(list.get(0));
        if (list.size() == 1 || value == null) {
            return (T) value;
        } else {
            list.remove(0);
            return get((Map) value, String.join(".", list));
        }
    }

    public static Integer getInteger(Map map, String params) {
        return Optional.ofNullable(getNumber(map, params, Integer::parseInt))
                .map(Number::intValue).orElse(null);
    }

    public static Long getLong(Map map, String params) {
        return Optional.ofNullable(getNumber(map, params, Long::parseLong))
                .map(Number::longValue).orElse(null);
    }

    public static Double getDouble(Map map, String params) {
        return Optional.ofNullable(getNumber(map, params, Double::parseDouble))
                .map(Number::doubleValue).orElse(null);
    }

    public static Number getNumber(Map map, String params, Function<String, Number> toNumber) {
        var result = get(map, params);
        if (result instanceof String) return toNumber.apply((String) result);
        else if (result instanceof Number) return (Number) result;
        else return null;
    }

}
