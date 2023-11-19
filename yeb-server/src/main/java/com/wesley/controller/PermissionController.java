package com.wesley.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wesley.pojo.Menu;
import com.wesley.pojo.MenuRole;
import com.wesley.pojo.RespBean;
import com.wesley.pojo.Role;
import com.wesley.service.MenuRoleService;
import com.wesley.service.MenuService;
import com.wesley.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/system/basic/permission")
public class PermissionController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private MenuRoleService menuRoleService;

    @ApiOperation(value = "get all permissions")
    @GetMapping("/")
    public List<Role> getAllRoles(){
        return roleService.list();
    }

    @ApiOperation(value = "add role")
    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {
        if (!role.getName().startsWith("ROLE_")) {
            role.setName("ROLE_" + role.getName());
        }
        if (roleService.save(role)) {
            return RespBean.success("add role successfully!");
        }
        return RespBean.error("role add failed!");
    }

    @ApiOperation(value = "delete role")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRole(@PathVariable Integer rid) {
        if (roleService.removeById(rid)) {
            return RespBean.success("delete role successfully!");
        }
        return RespBean.error("role delete failed!");
    }

    @ApiOperation(value="get all menus")
    @GetMapping("/menu")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @ApiOperation(value="inquiry menu id by role id")
    @GetMapping("/mid/{rid}")
    public List<Integer>getMidByRid(@PathVariable Integer rid){
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid", rid))
                .stream().map(MenuRole::getId).collect(Collectors.toList());
    }

    @ApiOperation(value ="update role menus")
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        return menuRoleService.updateMenuRole(rid, mids);
    }
}
