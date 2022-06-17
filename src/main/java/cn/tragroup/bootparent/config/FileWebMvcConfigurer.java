package cn.tragroup.bootparent.config;

import cn.tragroup.bootparent.auto.prop.FileHandleTrProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.text.MessageFormat;

/**
 * @author 耿传奇
 * @create 2020-10-14 09:07
 */
@Slf4j
public class FileWebMvcConfigurer implements WebMvcConfigurer {

    private final FileHandleTrProperties config;

    public FileWebMvcConfigurer(FileHandleTrProperties config) {
        this.config = config;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        var fileMapping = config.getMapping();
        var resourceHandlerPath = String.format("file:%s%s", config.getCanonicalRootPath(), File.separator);
        registry.addResourceHandler(fileMapping).addResourceLocations(resourceHandlerPath);
        System.out.println(MessageFormat.format("\n\n文件映射\npattern: {0}\npath: {1}\n\n", fileMapping, resourceHandlerPath));
    }
}
