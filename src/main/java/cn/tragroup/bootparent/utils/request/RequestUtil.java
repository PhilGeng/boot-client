package cn.tragroup.bootparent.utils.request;

import cn.tragroup.bootparent.constant.RequestConstant;
import cn.tragroup.bootparent.model.RequestUser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Nullable
    public static RequestUser getSessionUser() {
        return getSessionAttr(RequestConstant.USER);
    }

    public static void setRequestUser(@Nullable RequestUser requestUser) {
        setSessionAttr(RequestConstant.USER, requestUser);
    }

    public static void removeRequestUser() {
        removeSessionAttr(RequestConstant.USER);
    }

    public static HttpSession getSession() {
        return RequestUtil.getRequest().getSession();
    }

    public static void setSessionAttr(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getSessionAttr(String key) {
        return (T) getSession().getAttribute(key);
    }

    public static void removeSessionAttr(String key) {
        getSession().removeAttribute(key);
    }
}
