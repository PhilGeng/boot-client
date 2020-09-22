package cn.tragroup.bootparent.controller;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author GengChuanqi
 * @create 2020-06-13 13:05
 */
public abstract class BaseController<Service extends IService<Entity>, Entity>
        extends AbstractBaseController<Service, Entity>
        implements IList<Service, Entity>, IInsert<Service, Entity>, IGet<Service, Entity>, IDelete<Service, Entity>, IUpdate<Service, Entity> {

    public BaseController(Service baseService) {
        super(baseService);
    }


}
