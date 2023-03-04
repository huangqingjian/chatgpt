package com.chatgpt.configuration.secure;

import com.alibaba.fastjson.JSON;
import com.chatgpt.constant.Constant;
import com.chatgpt.util.JwtUtils;
import com.chatgpt.util.ServletUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 管理端自定义BasicAuthenticationFilter
 */
public class ManageAuthenticationFilter extends OncePerRequestFilter {
    private static final Logger log = LoggerFactory.getLogger(ManageUsernamePasswordAuthenticationFilter.class);

    private StringRedisTemplate stringRedisTemplate;
    private String[] openApi;

    public ManageAuthenticationFilter(StringRedisTemplate stringRedisTemplate, String[] openApi) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.openApi = openApi;
    }

    public void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain chain) throws IOException, ServletException {
        if(filterUrl(httpServletRequest.getRequestURI())) {
            chain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        String token = ServletUtil.getManageToken(httpServletRequest);
        if (StringUtils.isEmpty(token)) {
            throw new AuthenticationException("非法访问～");
        } else {
            try {
                // 获取用户
                String userStr = stringRedisTemplate.opsForValue().get(Constant.MANAGE_SESSION_PREFIX + JwtUtils.getUsername(token));
                UserDetailsDTO user = JSON.parseObject(userStr, UserDetailsDTO.class);
                if(user == null) {
                    throw new AuthenticationException("请先登录～");
                }
                UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authResult);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                throw new AuthenticationException(e.getMessage(), e);
            }
            chain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    private PathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 过滤url
     *
     * @param url
     * @throws ServletException
     */
    protected boolean filterUrl(String url) {
        for(String pattern : openApi) {
            boolean flag = pathMatcher.match(pattern, url);
            if(flag) {
                return true;
            }
        }
        return false;
    }

}
