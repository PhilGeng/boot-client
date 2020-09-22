package cn.tragroup.bootparent.utils.random;

import cn.tragroup.bootparent.utils.cellection.array.*;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

public class NumberWordRandom {

    private static final ConcurrentHashMap<String, String[]> TEMP = new ConcurrentHashMap<>();

    public static String make(int size, RandomType... types) {
        String[] dic = NumberWordRandom.getDic(types);
        var result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            var index = (int) (Math.random() * dic.length);
            result.append(dic[index]);
        }
        return result.toString();
    }


    public static String makeNumber(int size){
        return make(size,RandomType.Number);
    }

    public static String makeLow(int size){
        return make(size,RandomType.Low);
    }

    public static String makeUpper(int size){
        return make(size,RandomType.Upper);
    }

    public static String[] getDic(RandomType... types) {
        if (types == null || types.length == 0)
            throw new RuntimeException("随机字符类型最少为一个");

        StringBuilder builder = new StringBuilder();
        Arrays.stream(types).map(Enum::toString).forEachOrdered(builder::append);

        var key = builder.toString();
        String[] value = TEMP.get(key);
        if (value != null) return value;

        value = new String[]{};
        if (types.length == 1) {
            var type = types[0];
            value = type.value();
        } else for (RandomType type : types) {
            value = ArrayUtil.concat(value, type.value());
        }

        TEMP.put(key, value);
        return value;
    }

}
