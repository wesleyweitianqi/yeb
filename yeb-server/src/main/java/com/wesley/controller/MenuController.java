package com.wesley.controller;


import com.wesley.mapper.MenuMapper;
import com.wesley.pojo.Menu;
import com.wesley.service.IAdminService;
import com.wesley.service.MenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Wesley
 * @since 2023-11-05
 */
@Controller
@RequestMapping("/system/cfg")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @ApiOperation(value="getMenuByUserID")
    @GetMapping("/menu")
    private List<Menu> getMenusByAdminId(){
        return menuService.getMenusByAdminId();
    }
}

