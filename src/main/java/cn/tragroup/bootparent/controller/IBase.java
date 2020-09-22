package cn.tragroup.bootparent.controller;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author 耿传奇
 * @create 2020-07-04 17:29
 */
public interface IBase<Service extends IService<Entity>,Entity> {
    Service getBaseService();
}
