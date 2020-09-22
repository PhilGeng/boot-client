package cn.tragroup.bootparent.utils.query.model;


import java.lang.annotation.*;

/**
 * @author GengChuanqi
 * @create 2020-07-04 15:48
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Query {
    QueryType value() default QueryType.DEFAULT;
}
