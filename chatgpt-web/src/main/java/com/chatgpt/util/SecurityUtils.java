package com.chatgpt.util;

import com.chatgpt.configuration.secure.UserDetailsDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * security工具类
 */
public class SecurityUtils {
    /**
     * 获取当前用户
     *
     * @return
     */
    public static UserDetailsDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        if(principal == null) {
            return null;
        }
        if(principal instanceof UserDetailsDTO) {
            return (UserDetailsDTO) authentication.getPrincipal();
        }
        return null;
    }

    /**
     * 获取当前用户Id
     *
     * @return
     */
    public static Long getCurrentUserId() {
        UserDetailsDTO userDetails = getCurrentUser();
        return userDetails == null ? 0L : userDetails.getId();
    }
}
