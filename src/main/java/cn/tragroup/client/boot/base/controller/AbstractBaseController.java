package cn.tragroup.client.boot.base.controller;

import cn.tragroup.client.boot.base.model.HttpResult;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 耿传奇
 * @create 2020-07-04 17:39
 */
public abstract class AbstractBaseController<Service extends IService<Entity>, Entity> implements IBase<Service,Entity> {

    protected Service baseService;

    public AbstractBaseController(Service baseService) {
        this.baseService = baseService;
    }

    @Override
    public Service getBaseService() {
        return baseService;
    }

    protected HttpResult<Object> error(){
        return HttpResult.error();
    }
    protected HttpResult<Object> success(){
        return HttpResult.success();
    }
    protected <T> HttpResult<T> success(T data){
        return HttpResult.success(data);
    }
}
