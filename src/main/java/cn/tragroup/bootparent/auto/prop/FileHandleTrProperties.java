package cn.tragroup.bootparent.auto.prop;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.File;
import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author 耿传奇
 * @create_time 2022/6/9 15:17
 */

@ConfigurationProperties(prefix = "tr.file-handle")
@Slf4j
public class FileHandleTrProperties {

    private String mapping = "/file/**";

    private boolean enable = false;

    private String rootPath = "../upload";


    public String getCanonicalRootPath(){
        String path = "";

        try {
            path = new File(this.rootPath).getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }


    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }
}
