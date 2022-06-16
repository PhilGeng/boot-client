package cn.tragroup.bootparent.config;

import cn.tragroup.bootparent.auto.prop.MyBatisPlusTrProperties;
import cn.tragroup.bootparent.mp.DynamicTable;
import cn.tragroup.bootparent.mp.DynamicTableUtil;
import cn.tragroup.bootparent.utils.clazz.ClassUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.DynamicTableNameInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 耿传奇
 * @create 2020-09-23 17:27
 */
public class MyBatisPlusConfig {

    @Autowired
    MyBatisPlusTrProperties myBatisPlusTrProperties;

    /**
     * Mybatis Plus 拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(getDynamicTableNameInnerInterceptor());
        interceptor.addInnerInterceptor(getPageInnerInterceptor());
        return interceptor;
    }


    /**
     * 分页插件
     */
    InnerInterceptor getPageInnerInterceptor() {
        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor();
        pageInterceptor.setOverflow(false);
        return pageInterceptor;
    }


    InnerInterceptor getDynamicTableNameInnerInterceptor() {
        DynamicTableNameInnerInterceptor interceptor = new DynamicTableNameInnerInterceptor();
        MyBatisPlusTrProperties.DynamicTable dynamic = myBatisPlusTrProperties.getDynamic();
        var location = dynamic.getLocation();

        if(location != null){
            List<Class<?>> classList = ClassUtil.getClassByAnnotation(location, dynamic.getResourcePattern(), DynamicTable.class);

            HashMap<String,DynamicTable> cacheTables = new HashMap<>();

            for (Class<?> aClass : classList) {
                String name = aClass.getAnnotation(TableName.class).value();
                DynamicTable annotation = aClass.getAnnotation(DynamicTable.class);
                cacheTables.put(name, annotation);
            }

            interceptor.setTableNameHandler((String sql, String tableName) -> {
                DynamicTable annotation = cacheTables.get(tableName);
                if(annotation != null){
                    String pattern = annotation.pattern();
                    pattern = pattern.replaceAll("\\$\\{table\\}", tableName);
                    pattern = pattern.replaceAll("\\$\\{var\\}", DynamicTableUtil.get(tableName));
                    return pattern;
                }
                return tableName;
            });
        }


        return interceptor;
    }

}
