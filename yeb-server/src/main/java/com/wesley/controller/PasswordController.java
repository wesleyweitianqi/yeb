package com.wesley.controller;

import com.wesley.service.IAdminService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api( tags = "passwordController")
@RestController
public class PasswordController {
    @Autowired
    private IAdminService adminService;
    @Autowired
    private NewPassword newPassword;

    @PostMapping("/change-password")
    public void changePassword(@RequestBody NewPassword newPassword) {
        System.out.println(newPassword);
        System.out.println(newPassword.getNewPassword());
        // Invoke the service method to change the password for the "admin" user
        adminService.changePasswordForAdmin(String.valueOf(newPassword.getNewPassword()));

    }
}
