package cn.tragroup.client.boot.base.controller;

import cn.tragroup.client.boot.base.model.EditorModel;
import cn.tragroup.client.boot.base.model.HttpResult;
import cn.tragroup.client.boot.base.model.RequestUser;
import cn.tragroup.client.boot.base.utils.request.RequestUtil;
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
