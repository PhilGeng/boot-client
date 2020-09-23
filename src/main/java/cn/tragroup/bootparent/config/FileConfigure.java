package cn.tragroup.bootparent.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
@Slf4j
public class FileConfigure {
    /* 文件上传根目录 */
    public static String FILE_ROOT_PATH;

    @Value("${upload.path:../upload}")
    public void setUploadRootPath(String uploadRootPath) throws IOException {
        FILE_ROOT_PATH = new File(uploadRootPath).getCanonicalPath();
        log.info("文件路径为：{}",uploadRootPath);
    }

    public FileConfigure() {

    }
}