package cn.tragroup.bootparent.mp;

import java.lang.annotation.*;

/**
 * <p>
 *
 * </p>
 *
 * @author 耿传奇
 * @create_time 2021/9/26 15:46
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface DynamicTable {
    /**
     * <p>格式化最终的表名的格式，</p>
     * <p>占位符：</p>
     *  <p> ${table} - 格式化前表名</p>
     *  <p> ${var} - 增加的参数</p>
     * <br>
     * <p>如 此参数为 ${table}_${var}，并且此注解加到 User 类上,</p>
     * <p>User 类对应的表名为 `user`</p>
     * <p>在进行查询前，执行{@link DynamicTableUtil#set(String, String)}方法给${var}赋值</p>
     * <p>例如 {@code DynamicTableUtil.set("user","1")}</p>
     * <p>表示最终表名为 user_1</p>
     */
    String pattern() default "${table}_${var}";



}
