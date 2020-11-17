package cn.tragroup.bootparent.service.impl;

import cn.tragroup.bootparent.model.UuidModel;
import cn.tragroup.bootparent.service.UuidService;
import cn.tragroup.bootparent.utils.uuid.UUIDUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @author 耿传奇
 * @create 2020-10-15 10:40
 */
public class UuidServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements UuidService<T> {

    @Override
    public boolean save(T entity) {
        if(entity instanceof UuidModel){
            var uuidModel = (UuidModel) entity;
            uuidModel.setUuid(UUIDUtil.getUUID());
        }
        return super.save(entity);
    }

    @Override
    public T getByUUID(String uuid) {
        return query().eq("uuid", uuid).one();
    }
}
