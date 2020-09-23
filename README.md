# boot-client
快速创建spring boot项目




## 快速开始
导入包后，创建Application：将'xxx.xxx.xxx'换成你的包名
```java
@SpringBootApplication
@MapperScan("xxx.xxx.xxx")
@ComponentScan({PackageConstant.BASE_PACKAGE, "xxx.xxx.xxx"})
public class PanoramaApplication {
    public static void main(String[] args) {
        SpringApplication.run(PanoramaApplication.class, args);
    }
}
```
