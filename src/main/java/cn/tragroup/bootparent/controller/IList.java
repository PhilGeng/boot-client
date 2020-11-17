package cn.tragroup.bootparent.controller;

import cn.tragroup.bootparent.model.HttpResult;
import cn.tragroup.bootparent.model.PageData;
import cn.tragroup.bootparent.utils.query.QueryUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 耿传奇
 * @create 2020-07-04 17:28
 */
public interface IList<Service extends IService<Entity>, Entity> extends IBase<Service, Entity> {

    @GetMapping
    default HttpResult<Object> getList(PageData pageData, Entity entity) throws IllegalAccessException {
        QueryWrapper<Entity> wrapper;
        if (QueryUtils.hasQueryAnnotation(entity)) {
            wrapper = QueryUtils.getQueryWrapper(entity);
        } else {
            wrapper = Wrappers.query(entity);
        }
        if (pageData.getAsc() != null && pageData.getOrder() != null) {
            var asc = pageData.getAsc();
            wrapper.orderByAsc(asc, pageData.getOrder());
            wrapper.orderByDesc(!asc, pageData.getOrder());
        }
        return HttpResult.success(getBaseService().page(pageData.generatePage(), this.getListInterceptor(wrapper, pageData, entity)));
    }


    default QueryWrapper<Entity> getListInterceptor(QueryWrapper<Entity> wrapper, PageData pageData, Entity entity) {
        return wrapper;
    }

}
