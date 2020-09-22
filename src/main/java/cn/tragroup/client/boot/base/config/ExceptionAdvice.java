package cn.tragroup.client.boot.base.config;

import cn.tragroup.client.boot.base.model.HttpResult;
import cn.tragroup.client.boot.base.utils.json.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author GengChuanqi
 * @create 2020-06-17 17:51
 */
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object exception(MethodArgumentNotValidException e) {
        var fieldError = e.getBindingResult().getFieldError();
        log.error("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        return HttpResult.make(400, fieldError.getDefaultMessage(), null);
    }

    @ExceptionHandler(BindException.class)
    public HttpResult<Object> handleBindException(BindException ex) {
        var fieldError = ex.getBindingResult().getFieldError();
        log.error("参数校验异常:{}({})", fieldError.getDefaultMessage(),fieldError.getField());
        return HttpResult.make(400, fieldError.getDefaultMessage(), null);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public HttpResult<Object> requestNotReadable(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage(),ex);
        return HttpResult.make(400, "传参有误："+ex.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    public Object exception(Exception e) {
        log.error(e.getMessage(), e);
        return HttpResult.make(500, e.getMessage(), null);
    }


}
