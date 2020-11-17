package cn.tragroup.bootparent.service;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 耿传奇
 * @create 2020-10-15 10:39
 */
public interface UuidService<T> extends IService<T> {

    T getByUUID(String uuid);

}
