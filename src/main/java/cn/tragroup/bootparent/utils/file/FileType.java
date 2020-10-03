package cn.tragroup.bootparent.utils.file;

import java.util.Arrays;
import java.util.List;

/**
 * @author 耿传奇
 * @create 2020-07-30 15:06
 */
public enum FileType {
    IMAGE("png","jpg","jpeg","ico"),
    PPT("ppt","pptx"),
    EXCEL("xls","xlsx"),
    WORD("doc","docx"),
    VIDEO("mp4","avi"),
    UNKNOWN;

    private final List<String> types;

    FileType(String... types){
        this.types = Arrays.asList(types);
    }

    public static FileType get(String type){
        type = type.toLowerCase();
        for (FileType fileType : FileType.values()) {
            if( fileType.getTypes().contains(type)) return fileType;
        }
        return UNKNOWN;
    }

    public List<String> getTypes() {
        return types;
    }

}