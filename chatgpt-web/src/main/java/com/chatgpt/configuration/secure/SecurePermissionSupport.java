package com.chatgpt.configuration.secure;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 自定义security权限注解实现
 */
public class SecurePermissionSupport implements PermissionEvaluator {

    @Resource
    private SecurityProperty securityProperty;

    /**
     * 自定义security权限认证@hasPermission
     */
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        UserDetailsDTO user = (UserDetailsDTO) authentication.getPrincipal();
//        Set<String> permits = user.getPermits()
//                .stream()
//                .filter(e -> !StringUtils.isEmpty(e.getUrl()))
//                .map(e -> e.getUrl())
//                .collect(Collectors.toSet());
//        return permits.contains(permission);
        return true;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
