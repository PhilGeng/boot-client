package cn.tragroup.bootparent.utils.clazz;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.util.ClassUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author 耿传奇
 * @create_time 2021/9/26 16:04
 */
public class ClassUtil {

    public static List<Class<?>> getClassByAnnotation(String classPath, String resourcePattern, Class<? extends Annotation> annotation) {
        List<Class<?>> result = new ArrayList<>();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
//            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX +
//                    ClassUtils.convertClassNameToResourcePath(myBatisPlusTrProperties.getDynamicTablePackage()) +
//                    myBatisPlusTrProperties.getDynamicTableResourcePattern();
            String pattern = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                    + ClassUtils.convertClassNameToResourcePath(classPath)
                    + resourcePattern;

            Resource[] resources = resolver.getResources(pattern);
            CachingMetadataReaderFactory factory = new CachingMetadataReaderFactory(resolver);

            for (Resource resource : resources) {
                MetadataReader reader = factory.getMetadataReader(resource);
                AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();
                if (annotationMetadata.hasAnnotation(annotation.getName())) {
                    result.add(Class.forName(reader.getClassMetadata().getClassName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
