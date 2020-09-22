package cn.tragroup.bootparent.utils.request;

import cn.tragroup.bootparent.constant.RequestConstant;
import cn.tragroup.bootparent.model.RequestUser;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author GengChuanqi
 * @create 2020-06-17 17:38
 */
public class RequestUtil {

    @NotNull
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    @SuppressWarnings("unchecked")
    public static <T> T getSessionAttr(String key) {
        return (T) getRequest().getSession().getAttribute(key);
    }

    public static RequestUser getSessionUser() {
        return getSessionAttr(RequestConstant.USER);
    }
}
