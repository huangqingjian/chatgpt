package com.chatgpt.configuration.secure;

import com.chatgpt.constant.Constant;
import com.chatgpt.dto.UserDTO;
import com.chatgpt.service.UserService;
import com.chatgpt.util.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Security加载用户信息服务
 */
@Component
public class SecureUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserService userService;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] array = username.split(Constant.MAOHAO, Constant.ZERO);
        String mobile = array[0];
        String password = array[1];
        UserDTO user = userService.findByMobile(mobile);
        // 用户不存在，则注册
        if(user == null) {
            user = new UserDTO();
            user.setMobile(mobile);
            user.setPassword(passwordEncoder.encode(password));
            Long id = userService.save(user);
            user.setId(id);
        }
        UserDetailsDTO userInfo = BeanUtils.map(user, UserDetailsDTO.class);
        return userInfo;
    }

}
