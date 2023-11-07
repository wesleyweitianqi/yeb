package com.wesley.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wesley.pojo.Admin;
import com.wesley.pojo.RespBean;

import javax.servlet.http.HttpServletRequest;

public interface IAdminService extends IService<Admin> {
    RespBean login(String username, String password, HttpServletRequest request);
    Admin getAdminByUserName(String username);
}
