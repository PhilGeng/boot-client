package cn.tragroup.client.boot.base.controller;

import cn.tragroup.client.boot.base.model.HttpResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 耿传奇
 * @create 2020-07-04 17:33
 */
public interface IGet<Service extends IService<Entity>, Entity> extends IBase<Service, Entity> {

    @GetMapping("{id:\\d+}")
    default HttpResult<Entity> getOne(@PathVariable Number id) {
        return HttpResult.success(getBaseService().getById(id));
    }

}
