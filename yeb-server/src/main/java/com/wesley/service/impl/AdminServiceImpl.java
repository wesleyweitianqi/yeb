package com.wesley.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wesley.config.security.JwtTokenUtil;
import com.wesley.mapper.AdminRoleMapper;
import com.wesley.mapper.RoleMapper;
import com.wesley.pojo.Admin;
import com.wesley.mapper.AdminMapper;
import com.wesley.pojo.AdminRole;
import com.wesley.pojo.RespBean;
import com.wesley.pojo.Role;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wesley.service.IAdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Wesley
 * @since 2023-11-05
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private  UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private  IAdminService iAdminService;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    /**
     * return token after login
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request){
        String captcha =(String) request.getSession().getAttribute("captcha");
        System.out.println(captcha);
        System.out.println(code);
        if(StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)){
            return RespBean.error("captcha is wrong, please reenter");
        }
        //login
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(null == userDetails ){
            return RespBean.error("username or password is wrong");
        }
        if(!userDetails.isEnabled()){
            return RespBean.error("account is forbidden, please contact to admin");
        }

        //update security login object
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);


        //generate token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("login successfully", tokenMap);
    }

    @Override
    public Admin getAdminByUserName(String username){
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username", username).eq("enabled", true));
    }

    public void changePasswordForAdmin(String newPassword) {
        // Step 1: Retrieve the user by username
        Admin admin = iAdminService.getAdminByUserName("admin");

        // Step 2: Update the password
        if (admin != null) {
            admin.setPassword(passwordEncoder.encode(newPassword));

            // Step 3: Save the updated user to the database
            iAdminService.updateById(admin);
        }
    }

    @Override
    public List<Role> getRoles(Integer AdminId) {
        return roleMapper.getRoles(AdminId);
    }

    @Override
    public  List<Admin> getAllAdmins(String keywords){
        return adminMapper.getAllAdmins(((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(), keywords);
    }

    @Override
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId", adminId));
        Integer res = adminRoleMapper.addRole(adminId, rids);
        if(rids.length == res){
            return RespBean.success("update admin roles successfully");
        }
        return RespBean.error("update admin roles failed");
    }

    @Override
    public RespBean updatePassword(String oldPass, String pass, Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(oldPass, admin.getPassword())){
            admin.setPassword(encoder.encode(pass));
            int result = adminMapper.updateById(admin);
            if(1 == result) return RespBean.success("update successfully");
            return RespBean.error("update failed");
        }
        return RespBean.error("update failed");
    }

}
