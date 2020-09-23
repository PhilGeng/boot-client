# boot-client
快速创建spring boot项目




## 快速开始
导入包后，创建Application
```java
@SpringBootApplication
@ComponentScan("cn.tragroup.bootparent") // 扫描预设包
public class PanoramaApplication {
    public static void main(String[] args) {
        SpringApplication.run(PanoramaApplication.class, args);
    }
}
```
