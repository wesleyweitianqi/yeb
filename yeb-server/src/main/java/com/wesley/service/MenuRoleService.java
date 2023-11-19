package com.wesley.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wesley.pojo.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wesley.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wesley
 * @since 2023-11-05
 */
public interface MenuRoleService extends IService<MenuRole> {
    RespBean updateMenuRole(Integer rid, Integer[] mids);


}
