package com.chatgpt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * vip管理器
 *
 * author: huangqj
 * date: 2022-11-20 11:27:16
 */
@Api(tags = "vip管理器", value = "Vip.Manager")
@Controller
@RequestMapping(value="/")
public class VipController extends BaseController{
    private static final Logger log = LoggerFactory.getLogger(VipController.class);

    private static final String INDEX = "/vip/index";

    /**
     * vip页
     *
     * @return
     */
    @ApiOperation(value = "vip页")
    @RequestMapping(value = "/vip.html")
    public String loginPage() {
        return INDEX;
    }


}
