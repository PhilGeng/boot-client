package cn.tragroup.bootparent.auto.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * author: 耿传奇
 * Date: 2021-9-8 10:32
 * Description:
 */

@ConfigurationProperties(prefix = "tr.redis")
public class RedisTrProperties {
    private boolean enable = false;


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
