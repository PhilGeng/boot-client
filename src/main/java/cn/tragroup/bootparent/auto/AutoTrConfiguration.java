package cn.tragroup.bootparent.auto;

import cn.tragroup.bootparent.auto.prop.FileHandleTrProperties;
import cn.tragroup.bootparent.auto.prop.MyBatisPlusTrProperties;
import cn.tragroup.bootparent.auto.prop.RedisTrProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * author: 耿传奇
 * Date: 2021-9-8 10:35
 * Description:
 */
@Configuration
@EnableConfigurationProperties({
        FileHandleTrProperties.class,
        MyBatisPlusTrProperties.class,
        RedisTrProperties.class
})
public class AutoTrConfiguration {
}
