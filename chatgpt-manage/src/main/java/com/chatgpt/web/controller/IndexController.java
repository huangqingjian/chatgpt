package com.chatgpt.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页管理器
 *
 * author: huangqj
 * date: 2022-11-10 10:43:48
 */
@Api(tags = "主页管理器", value = "Index.Manager")
@Controller
@RequestMapping(value="/")
public class IndexController extends BaseController{
    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    private static final String INDEX = "/index";
    private static final String CONSOLE = "/console";

    /**
     * 主页
     *
     * @return
     */
    @ApiOperation(value = "主页")
    @RequestMapping(value = "/")
    public String index() {
        return INDEX;
    }

    /**
     * console
     *
     * @return
     */
    @ApiOperation(value = "console")
    @RequestMapping(value = "/console.html")
    public String console() {
        return CONSOLE;
    }

}
