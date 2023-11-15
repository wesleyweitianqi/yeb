package com.wesley.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;


@Component
public class CustomUrlDecisonManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute: collection){
            String needRole = configAttribute.getAttribute();
            if("ROLE_LOGIN".equals(needRole)){
                if(authentication instanceof AnonymousAuthenticationToken){
                    throw new AccessDeniedException("please login first");
                }else {
                    return;
                }
            }
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for(GrantedAuthority authority: authorities){
                if(authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }
        throw new AccessDeniedException("permission not allowed, please contact admin");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return false;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
