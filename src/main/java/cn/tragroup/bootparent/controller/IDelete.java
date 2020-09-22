package cn.tragroup.bootparent.controller;

import cn.tragroup.bootparent.model.HttpResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 耿传奇
 * @create 2020-07-04 17:33
 */
public interface IDelete<Service extends IService<Entity>, Entity> extends IBase<Service, Entity> {

    @DeleteMapping("{id:\\d+}")
    default HttpResult<Object> remove(@PathVariable Number id) {
        return getBaseService().removeById(id) ? HttpResult.success() : HttpResult.error();
    }

}
