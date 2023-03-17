package com.chatgpt.controller;

import com.chatgpt.dto.*;
import com.chatgpt.service.CostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 消费管理器
 *
 * author: huangqj
 * date: 2022-11-20 11:27:16
 */
@Api(tags = "消费管理器", value = "cost.Manager")
@Controller
@RequestMapping(value="/cost")
public class CostController extends BaseController{
    private static final Logger log = LoggerFactory.getLogger(CostController.class);

    @Autowired
    private CostService costService;

    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "查询")
    @RequestMapping(value = "/list")
    @ResponseBody
    public ResponseDTO<PageDTO<CostDTO>> list(CommonQueryDTO queryDTO) {
        queryDTO.setUserId(getCurrentUserId());
        PageDTO<CostDTO> page = costService.list(queryDTO);
        return ResponseDTO.success(page);
    }

}
