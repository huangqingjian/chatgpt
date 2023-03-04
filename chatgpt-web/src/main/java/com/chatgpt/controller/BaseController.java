package com.chatgpt.controller;

import com.chatgpt.configuration.secure.UserDetailsDTO;
import com.chatgpt.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * 基础控制器
 */
public class BaseController {
    @Value("${default.model.id:0}")
    protected Long DEFAULT_MODEL_ID;
    @Value("${default.index.count:6}")
    protected Integer DEFAULT_INDEX_COUNT;
    @Value("${default.page:1}")
    protected Integer DEFAULT_PAGE;
    @Value("${default.page.size:8}")
    protected Integer DEFAULT_PAGE_SIZE;

    @Autowired
    private HttpServletRequest request;

    /**
     * 添加RequestURI
     *
     * @param model
     */
    @ModelAttribute
    private void before(Model model) {
        model.addAttribute("requestURL", request.getRequestURI());
        model.addAttribute("currentUser", getCurrentUser());
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public UserDetailsDTO getCurrentUser() {
        return SecurityUtils.getCurrentUser();
    }

    /**
     * 获取当前用户Id
     *
     * @return
     */
    public Long getCurrentUserId() {
        return SecurityUtils.getCurrentUserId();
    }
}
