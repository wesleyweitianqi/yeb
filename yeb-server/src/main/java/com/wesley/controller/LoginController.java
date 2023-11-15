package com.wesley.controller;

import com.wesley.pojo.Admin;
import com.wesley.pojo.AdminLoginParam;
import com.wesley.pojo.RespBean;
import com.wesley.service.AdminService;
import com.wesley.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Api(tags = "LoginController")
@RestController
public class LoginController {
    @Autowired
    private AdminLoginParam adminLoginParam;

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "return token after login")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword(), adminLoginParam.getCode(), request);
    }

    @ApiOperation(value = "get userinfo")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){
        System.out.println(principal);
        if(null == principal){

            return null;
        }
        String username = principal.getName();
        System.out.println(username);
        Admin admin  = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }

    @ApiOperation(value = "logout")
    @PostMapping("/logout")
    public RespBean logout(){
        return RespBean.success("logout successfully");
    }
}
