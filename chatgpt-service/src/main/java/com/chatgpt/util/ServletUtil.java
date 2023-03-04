package com.chatgpt.util;

import com.chatgpt.constant.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Servlet工具类
 */

public class ServletUtil {
    /**
     * 获取HttpServletRequest对象
     *
     * Return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest();
    }

    /**
     * 获取 HttpServletResponse对象
     *
     * Return
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getResponse();
    }

    /**
     * HttpServletSession 对象
     *
     * Return
     */
    public static HttpSession getSession() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest().getSession();
    }

    /**
     * 判断是否为Ajax请求
     *
     * @param request
     * @return
     */
    public static Boolean isAjax(HttpServletRequest request) {
        String requestType = request.getHeader(Constant.Header.X_REQUESTED_WITH);
        if (Constant.Header.XML_HTTP_REQUEST.equals(requestType)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Response对象写出数据
     *
     * @param msg
     */
    public static void write(String msg) throws IOException {
        HttpServletResponse response = getResponse();
        response.setHeader(Constant.Header.CONTENT_TYPE, Constant.CONTENT_TYPE_JSON);
        response.setCharacterEncoding(Constant.UTF_8);
        response.getWriter().write(msg);
    }

    /**
     * Response对象写出数据
     *
     * @param
     * @param
     */
    public static void write(HttpServletResponse response, String msg) throws IOException {
        response.setStatus(HttpStatus.OK.value());
        response.setHeader(Constant.Header.CONTENT_TYPE, Constant.CONTENT_TYPE_JSON);
        response.setCharacterEncoding(Constant.UTF_8);
        response.getWriter().write(msg);
    }

    /**
     * 获取查询参数
     */
    public static String getQueryParam() {
        return getRequest().getQueryString();
    }

    /**
     * 获取请求地址
     */
    public static String getRequestURI() {
        return getRequest().getRequestURI();
    }

    /**
     * 获取客户端来源
     * */
    public static String getIpAddress() {
        HttpServletRequest request = getRequest();
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                // 根据网卡取本机配置的IP
                try {
                    ipAddress = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        if (ipAddress != null && ipAddress.length() > 15) {
            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 获取manage token
     *
     * @param request
     * @return
     */
    public static String getManageToken(HttpServletRequest request) {
        String token = request.getHeader(Constant.MANAGE_SESSION);
        if(!StringUtils.isEmpty(token)) {
            return token;
        }
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Constant.MANAGE_SESSION)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 获取admin token
     *
     * @param request
     * @return
     */
    public static String getAdminToken(HttpServletRequest request) {
        String token = request.getHeader(Constant.ADMIN_SESSION);
        if(!StringUtils.isEmpty(token)) {
            return token;
        }
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Constant.ADMIN_SESSION)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 获取admin token
     *
     * @param request
     * @return
     */
    public static String getWebToken(HttpServletRequest request) {
        String token = request.getHeader(Constant.WEB_SESSION);
        if(!StringUtils.isEmpty(token)) {
            return token;
        }
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(Constant.WEB_SESSION)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

}
