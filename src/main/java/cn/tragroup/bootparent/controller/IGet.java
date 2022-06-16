package cn.tragroup.bootparent.controller;

import cn.tragroup.bootparent.model.HttpResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;

/**
 * @author 耿传奇
 * @create 2020-07-04 17:33
 */
public interface IGet<Service extends IService<Entity>, Entity> extends IBase<Service, Entity> {

    @GetMapping("{id}")
    default HttpResult<Entity> getOne(@PathVariable Serializable id) {
        return HttpResult.success(getBaseService().getById(id));
    }

}
