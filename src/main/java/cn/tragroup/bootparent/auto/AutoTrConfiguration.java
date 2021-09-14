package cn.tragroup.bootparent.auto;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * author: 耿传奇
 * Date: 2021-9-8 10:35
 * Description:
 */
@Configuration
@EnableConfigurationProperties({
        MyBatisPlusTrProperties.class,
        RedisTrProperties.class
})
public class AutoTrConfiguration {
}
