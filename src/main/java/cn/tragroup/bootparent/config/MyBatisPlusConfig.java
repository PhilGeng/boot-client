package cn.tragroup.bootparent.config;

import cn.tragroup.bootparent.auto.MyBatisPlusTrProperties;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
        interceptor.addInnerInterceptor(getPageInnerInterceptor());
        return interceptor;
    }


    /**
     * 分页插件
     */
    InnerInterceptor getPageInnerInterceptor(){
        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor(myBatisPlusTrProperties.getDbType());
        pageInterceptor.setOverflow(false);
        return pageInterceptor;
    }

}
