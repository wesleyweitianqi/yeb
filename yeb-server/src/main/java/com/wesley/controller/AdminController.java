package com.wesley.controller;


import com.wesley.pojo.Admin;
import com.wesley.service.AdminService;
import com.wesley.service.IAdminService;
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
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "get all admins")
    @GetMapping("/")
    public List<Admin> getAllAdmins(String keyword){
        return adminService.getAllAdmins(keyword);
    }
}

