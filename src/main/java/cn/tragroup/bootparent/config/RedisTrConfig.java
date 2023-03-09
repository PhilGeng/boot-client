package cn.tragroup.bootparent.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * author: 耿传奇
 * Date: 2022-7-21 16:31
 * Description:
 */
@Configuration
public class RedisTrConfig {

    @Bean
    @ConditionalOnProperty(value = "tr.redis.enable", havingValue = "true")
    public RedisConfig redisConfig(){
        return new RedisConfig();
    }

}
