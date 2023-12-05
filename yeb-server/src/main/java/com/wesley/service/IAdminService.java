package com.wesley.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wesley.pojo.Admin;
import com.wesley.pojo.Menu;
import com.wesley.pojo.RespBean;
import com.wesley.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IAdminService extends IService<Admin> {
    RespBean login(String username, String password, String code, HttpServletRequest request);
    Admin getAdminByUserName(String username);
    public void changePasswordForAdmin(String newPassword);
    List<Role> getRoles(Integer AdminId);

    List<Admin> getAllAdmins(String keywords);
    RespBean updateAdminRole(Integer adminId, Integer[] rids);

    RespBean updatePassword(String oldPass, String pass, Integer adminId);
}
