package cn.tragroup.bootparent.config;

import cn.tragroup.bootparent.auto.prop.FileHandleTrProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 *
 * </p>
 *
 * @author 耿传奇
 * @create_time 2022/6/9 15:48
 */
@Configuration
public class FileHandleTrConfig {

    @Bean
    @ConditionalOnProperty(value = "tr.file-handle.enable", havingValue = "true")
    FileWebMvcConfigurer fileWebMvcConfigurer(FileHandleTrProperties config) {
        return new FileWebMvcConfigurer(config);
    }


}
