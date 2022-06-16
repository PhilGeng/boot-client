package cn.tragroup.bootparent.controller;

import cn.tragroup.bootparent.model.HttpResult;
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

    protected <T>  HttpResult<T> error(){
        return HttpResult.error();
    }
    protected <T>  HttpResult<T> success(){
        return HttpResult.success();
    }
    protected <T> HttpResult<T> success(T data){
        return HttpResult.success(data);
    }
}
