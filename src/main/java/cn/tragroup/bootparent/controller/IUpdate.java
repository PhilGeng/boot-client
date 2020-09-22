package cn.tragroup.bootparent.controller;

import cn.tragroup.bootparent.model.HttpResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 耿传奇
 * @create 2020-07-04 17:33
 */
public interface IUpdate<Service extends IService<Entity>, Entity> extends IBase<Service, Entity> {

    @PatchMapping("{id:\\d+}")
    default HttpResult<Entity> change(@RequestBody Entity entity) {
        return HttpResult.success(getBaseService().updateById(entity) ? entity : null);
    }

}
