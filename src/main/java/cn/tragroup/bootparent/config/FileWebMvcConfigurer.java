package cn.tragroup.bootparent.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.text.MessageFormat;

/**
 * @author 耿传奇
 * @create 2020-10-14 09:07
 */
public interface FileWebMvcConfigurer extends WebMvcConfigurer {

    default void addResourceHandlers(ResourceHandlerRegistry registry) {
        var fileMapping = "/file/**";
        var resourceHandlerPath = String.format("file:%s%s", FileConfigure.FILE_ROOT_PATH, File.separator);
        registry.addResourceHandler(fileMapping).addResourceLocations(resourceHandlerPath);
        System.out.println(MessageFormat.format("\n\n文件映射\npattern: {0}\npath: {1}\n\n", fileMapping, resourceHandlerPath));
    }
}
