package com.wesley.service;

import com.wesley.mapper.MenuMapper;
import com.wesley.pojo.Admin;
import com.wesley.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Wesley
 * @since 2023-11-05
 */
public interface MenuService extends IService<Menu> {
    public List<Menu> getMenusByAdminId();
    List<Menu> getMenusWithRole();

    List<Menu> getAllMenus();
}
