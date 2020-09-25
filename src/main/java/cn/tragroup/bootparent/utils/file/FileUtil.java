package cn.tragroup.bootparent.utils.file;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author 耿传奇
 * @create 2020-09-25 10:00
 */
@Slf4j
public class FileUtil {

    public static List<MultipartFile> sort(MultipartFile[] fileList) {
        var pattern = Pattern.compile("(.*?)(\\d+)(.*?)");
        return Arrays.stream(fileList).sorted(Comparator.comparingInt(i -> {
            var name = i.getOriginalFilename();
            var matcher = pattern.matcher(Objects.requireNonNull(name, "文件名为空"));
            if (matcher.find())
                return Integer.parseInt(matcher.group(2));
            else return 0;
        })).collect(Collectors.toList());
    }

}
