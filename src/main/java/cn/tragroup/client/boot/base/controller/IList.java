package cn.tragroup.client.boot.base.controller;

import cn.tragroup.client.boot.base.model.HttpResult;
import cn.tragroup.client.boot.base.model.PageData;
import cn.tragroup.client.boot.base.utils.query.QueryUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 耿传奇
 * @create 2020-07-04 17:28
 */
public interface IList<Service extends IService<Entity>,Entity> extends IBase<Service,Entity> {

    @GetMapping
    default HttpResult<Object> getList(PageData pageData, Entity entity) throws IllegalAccessException{
        QueryWrapper<Entity> wrapper;
        if(QueryUtils.hasQueryAnnotation(entity)){
            wrapper = QueryUtils.getQueryWrapper(entity);
        }else{
            wrapper = Wrappers.query(entity);
        }
        return HttpResult.success(getBaseService().page(pageData.generatePage(), this.getListInterceptor(wrapper)));
    }


    default QueryWrapper<Entity> getListInterceptor(QueryWrapper<Entity> wrapper) {
        return wrapper;
    }

}
