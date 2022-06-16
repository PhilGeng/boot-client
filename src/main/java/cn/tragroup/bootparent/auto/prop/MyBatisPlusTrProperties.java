package cn.tragroup.bootparent.auto.prop;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * author: 耿传奇
 * Date: 2021-9-8 10:32
 * Description:
 */

@ConfigurationProperties(prefix = "tr.mybatis-plus")
public class MyBatisPlusTrProperties {

    private DbType dbType = DbType.MYSQL;

    private DynamicTable dynamic = new DynamicTable();


    public static class DynamicTable {
        private String location;
        private String resourcePattern = "/**/*.class";
        private String requestKey = "dynamic-table-value";

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getResourcePattern() {
            return resourcePattern;
        }

        public void setResourcePattern(String resourcePattern) {
            this.resourcePattern = resourcePattern;
        }

        public String getRequestKey() {
            return requestKey;
        }

        public void setRequestKey(String requestKey) {
            this.requestKey = requestKey;
        }
    }


    public DbType getDbType() {
        return dbType;
    }

    public void setDbType(DbType dbType) {
        this.dbType = dbType;
    }

    public DynamicTable getDynamic() {
        return dynamic;
    }

    public void setDynamic(DynamicTable dynamic) {
        this.dynamic = dynamic;
    }
}
