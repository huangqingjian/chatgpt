package com.chatgpt.web.controller;

import com.chatgpt.constant.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;


/**
 * 登陆管理器
 *
 * author: huangqj
 * date: 2022-10-31 16:38:56
 */
@Api(tags = "登陆管理器", value = "Login.Manager")
@Controller
@RequestMapping(value="")
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private static final String LOGIN = "/login";

    /**
     * 登陆页
     *
     * @return
     */
    @ApiOperation(value = "登陆页")
    @RequestMapping(value = "/login.html")
    public String login(Model model) {
        model.addAttribute("token", UUID.randomUUID().toString().replaceAll(Constant.ZX, Constant.EMPTY));
        return LOGIN;
    }

}
