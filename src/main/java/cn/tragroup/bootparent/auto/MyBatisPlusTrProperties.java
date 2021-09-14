package cn.tragroup.bootparent.auto;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * author: 耿传奇
 * Date: 2021-9-8 10:32
 * Description:
 */
@Data
@ConfigurationProperties(prefix = "tr.mybatis-plus")
public class MyBatisPlusTrProperties {
    private DbType dbType = DbType.MYSQL;
}
