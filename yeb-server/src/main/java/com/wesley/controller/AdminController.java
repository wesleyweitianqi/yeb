package com.wesley.controller;


import com.wesley.pojo.Admin;
import com.wesley.pojo.RespBean;
import com.wesley.pojo.Role;
import com.wesley.service.AdminService;
import com.wesley.service.IAdminService;
import com.wesley.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "get all admins")
    @GetMapping("/")
    public List<Admin> getAllAdmins(String keyword){
        return adminService.getAllAdmins(keyword);
    }

    @ApiOperation(value = "update Admin")
    @PutMapping("/")
    public RespBean updateAdmin(@RequestBody Admin admin){
        if(adminService.updateById(admin)){
            return  RespBean.success("update admin successfully");
        }
        return RespBean.error("update admin failed");
    }

    @ApiOperation(value = "delete admin")
    @DeleteMapping("/{id}")
    public RespBean deleteAdmin(@PathVariable Integer id) {
        if(adminService.removeById(id)){
            return RespBean.success("delete admin successfully");
        }
        return  RespBean.error("delete admin failed");
    }

    @ApiOperation(value = "get all roles")
    @GetMapping("/role")
    public List<Role> getAllRoles(){
        List<Role> list = roleService.list();
        return roleService.list();
    }

    @ApiOperation(value = "update admin roles")
    @PutMapping("/role")
    public RespBean updateAdminRole(Integer adminId, Integer[] rids){
        return adminService.updateAdminRole(adminId, rids);
    }
}

