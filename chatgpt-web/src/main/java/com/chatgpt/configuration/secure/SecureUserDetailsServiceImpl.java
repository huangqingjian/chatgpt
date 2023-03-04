package com.chatgpt.configuration.secure;

import com.chatgpt.dto.UserDTO;
import com.chatgpt.enums.UserType;
import com.chatgpt.service.UserService;
import com.chatgpt.util.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Security加载用户信息服务
 */
@Component
public class SecureUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDTO user = userService.findByMobileAndType(username, UserType.CUSTOMER.getValue());
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found~");
        }
        UserDetailsDTO userInfo = BeanUtils.map(user, UserDetailsDTO.class);
        return userInfo;
    }

}
