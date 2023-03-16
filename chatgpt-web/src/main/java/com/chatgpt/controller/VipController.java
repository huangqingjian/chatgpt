package com.chatgpt.controller;

import com.chatgpt.dto.VipDTO;
import com.chatgpt.dto.ResponseDTO;
import com.chatgpt.dto.VipBuyDTO;
import com.chatgpt.service.VipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    private static final String SUCCESS = "/vip/success";

    @Autowired
    private VipService vipService;

    /**
     * vip页
     *
     * @param model
     * @return
     */
    @ApiOperation(value = "vip页")
    @RequestMapping(value = "/vip.html")
    public String index(Model model) {
        List<VipDTO> vips = vipService.listAll();
        model.addAttribute("vips", vips);
        return INDEX;
    }

    /**
     * vip开通成功页
     *
     * @return
     */
    @ApiOperation(value = "vip开通成功页")
    @RequestMapping(value = "/vip/success.html")
    public String success() {
        return SUCCESS;
    }

    /**
     * 购买vip
     *
     * @return
     */
    @ApiOperation(value = "购买vip")
    @PostMapping(value = "/vip/buy")
    @ResponseBody
    public ResponseDTO<Boolean> buy(@RequestBody VipBuyDTO vipBuy) {
        vipService.buy(vipBuy);
        return ResponseDTO.success(Boolean.TRUE);
    }

}
