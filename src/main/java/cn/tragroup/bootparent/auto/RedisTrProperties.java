package cn.tragroup.bootparent.auto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * author: 耿传奇
 * Date: 2021-9-8 10:32
 * Description:
 */
@Data
@ConfigurationProperties(prefix = "tr.redis")
public class RedisTrProperties {
    private Boolean enable = true;
}
