package cn.tragroup.bootparent.controller;

import cn.tragroup.bootparent.model.EditorModel;
import cn.tragroup.bootparent.model.HttpResult;
import cn.tragroup.bootparent.model.RequestUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author GengChuanqi
 * @create 2020-06-17 17:36
 */

public abstract class EditorBaseController<Service extends IService<Entity>, Entity> extends BaseController<Service, Entity> {

    public EditorBaseController(Service baseService) {
        super(baseService);
    }

    public abstract RequestUser getRequestUser();

    @Override
    public HttpResult<Entity> add(@RequestBody Entity entity) {
        if (entity instanceof EditorModel)
            ((EditorModel) entity).setCreator(getRequestUser());
        return HttpResult.success(baseService.save(entity) ? entity : null);
    }

    @Override
    public HttpResult<Entity> change(@RequestBody Entity entity) {
        if (entity instanceof EditorModel)
            ((EditorModel) entity).setUpdater(getRequestUser());
        return HttpResult.success(baseService.updateById(entity) ? entity : null);
    }


}
