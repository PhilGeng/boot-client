package cn.tragroup.bootparent.utils.uuid;

import java.util.UUID;

/**
 * @author 耿传奇
 * @create 2020-10-09 10:10
 */
public class UUIDUtil {

    public static String getBaseUUID(){
        return UUID.randomUUID().toString();
    }

    public static String getUUID(){
        return UUIDUtil.getBaseUUID().replaceAll("-","");
    }
}
