# boot-client
快速创建spring boot项目






## 快速开始

引入
```xml
<dependency>
    <groupId>cn.tragroup</groupId>
    <artifactId>quick-client-spring-boot-starter</artifactId>
    <version>${lastVer5sion}</version>
</dependency>
```


```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

你将会拥有
- 默认的json格式化配置。
- 默认的redis配置 可以用tr.redis.enable关闭redis。
- 默认的mybatis-plus配置 包含分页插件 数据库类型使用tr.mybatis-plus.db-type配置。
- 默认的全局异常拦截。
- 默认的全局返回格式为HttpResult。
- 基础的controller，包含getlist getOne remove add update的默认接口。
- 可以在字段上增加@Query更改默认的getList的字段筛选条件 比如Like eq等等。
