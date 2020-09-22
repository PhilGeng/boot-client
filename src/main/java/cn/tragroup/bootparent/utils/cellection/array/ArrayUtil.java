package cn.tragroup.bootparent.utils.cellection.array;

import java.util.Arrays;

/**
 * @author 耿传奇
 * @create 2020-09-21 18:02
 */
public class ArrayUtil {

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
