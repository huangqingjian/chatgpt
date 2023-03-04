package com.chatgpt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 错误管理器
 *
 * author: huangqj
 * date: 2022-11-29 16:20:34
 */
@Api(tags = "错误管理器", value = "Error.Manager")
@Controller
@RequestMapping(value="")
public class ErrorController extends BaseController{
    private static final Logger log = LoggerFactory.getLogger(ErrorController.class);

    private static final String _404 = "/404";
    private static final String _500 = "/500";

    /**
     * 404错误页
     *
     * @return
     */
    @ApiOperation(value = "404错误页")
    @RequestMapping(value = "/404.html")
    public String _404() {
        return _404;
    }

    /**
     * 500错误页
     *
     * @return
     */
    @ApiOperation(value = "500错误页")
    @RequestMapping(value = "/500.html")
    public String _500() {
        return _500;
    }

}
