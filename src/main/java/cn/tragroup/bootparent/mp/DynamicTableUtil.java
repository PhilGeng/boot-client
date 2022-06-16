package cn.tragroup.bootparent.mp;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author 耿传奇
 * @create_time 2021/9/27 16:15
 */
public class DynamicTableUtil {

    private static final ThreadLocal<Map<String,String>> DYNAMIC_TABLE_VAR_MAP = new ThreadLocal<>();

    public static void set(String tableName, String var){
        getMap().put(tableName,var);
    }

    public static String get(String tableName){
        return getMap().get(tableName);
    }

    private static Map<String,String> getMap(){
        Map<String, String> map = DYNAMIC_TABLE_VAR_MAP.get();
        if(map == null) {
            map = new HashMap<>();
            DYNAMIC_TABLE_VAR_MAP.set(map);
        }
        return map;
    }


}
