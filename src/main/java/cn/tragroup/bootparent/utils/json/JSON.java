package cn.tragroup.bootparent.utils.json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * 自己封装的jackson json转换器
 */
public class JSON {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
//                .enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
        ;
    }

    /**
     * 将json字符串转换成实体类
     *
     * @param json    要转换的json字符串
     * @param target0 返回的类
     * @param targets 类的多个泛型的类型
     * @param <T>     返回类型
     * @return 返回转换结果，失败则为null
     */
    public static <T> T fromJson(String json, Class<?> target0, Class<?>... targets) {
        T result = null;
        try {
            result = objectMapper.readValue(json, objectMapper.getTypeFactory().constructParametricType(target0, targets));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将对象转换为json字符串
     *
     * @param o 要转换的对象
     * @return 返回转换结果，失败则为null
     */
    public static String toJson(Object o) {
        String result = null;
        try {
            result = objectMapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
