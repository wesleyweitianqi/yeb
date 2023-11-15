package com.wesley.config;

import com.wesley.pojo.Menu;
import com.wesley.pojo.Role;
import com.wesley.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {
    @Autowired
    private MenuService menuService;

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {

        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //get request url
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        //get role menus
        List<Menu> menus = menuService.getMenusWithRole();
        for (Menu menu : menus) {
            //judge whether request url match any in role menu
            if(antPathMatcher.match(menu.getUrl(), requestUrl)){
                String[] str = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                return SecurityConfig.createList(str);
            }
        }
        //login_default if no match in menu
        return SecurityConfig.createList("ROLE_LOGIN");
    }
}
