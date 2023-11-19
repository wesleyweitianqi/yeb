package com.wesley.controller;

import com.wesley.pojo.RespBean;
import com.wesley.pojo.Role;
import com.wesley.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/system/basic/permission")
public class PermissionController {
    @Autowired
    private RoleService roleService;

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
}
