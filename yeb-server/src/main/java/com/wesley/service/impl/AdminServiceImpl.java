package com.wesley.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wesley.config.security.JwtTokenUtil;
import com.wesley.pojo.Admin;
import com.wesley.mapper.AdminMapper;
import com.wesley.pojo.RespBean;
import com.wesley.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wesley.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
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

    /**
     * return token after login
     * @param username
     * @param password
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password, HttpServletRequest request){

        //login
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(null == userDetails || passwordEncoder.matches(password, userDetails.getPassword())){
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
}
