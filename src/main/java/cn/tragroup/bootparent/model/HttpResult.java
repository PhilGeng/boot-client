package cn.tragroup.bootparent.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class HttpResult<T> implements Serializable {

    private int code;

    private String msg;

    private T data;

    public HttpResult(int code) {
        this.code = code;
    }

    public HttpResult(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public HttpResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> HttpResult<T> error() {
        return HttpResult.make(500, "error", null);
    }

    public static <T> HttpResult<T> error(String msg) {
        return HttpResult.make(500, msg, null);
    }

    public static <T> HttpResult<T> error(T data) {
        return HttpResult.make(500, "error", data);
    }

    public static <T> HttpResult<T> success() {
        return HttpResult. make(200, "success", null);
    }

    public static <T> HttpResult<T> success(T data) {
        return HttpResult.make(200, "success", data);
    }

    public static <T> HttpResult<T> make(int code, String msg, T data) {
        return new HttpResult<>(code, msg, data);
    }

    public void setMessage(String msg){
        this.msg = msg;
    }

    public void setStatus(Integer code){
        this.code = code;
    }

}
