package com.chatgpt.controller;

import com.chatgpt.dto.AnswerDTO;
import com.chatgpt.dto.QuestionDTO;
import com.chatgpt.dto.ResponseDTO;
import com.chatgpt.service.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 聊天管理器
 *
 * author: huangqj
 * date: 2022-11-20 11:27:16
 */
@Api(tags = "聊天管理器", value = "Chat.Manager")
@Controller
@RequestMapping(value="/")
public class ChatController extends BaseController{
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    private static final String INDEX = "/chat/index";
    @Autowired
    private ChatService chatService;

    /**
     * 主页
     *
     * @param model
     * @return
     */
    @ApiOperation(value = "主页")
    @RequestMapping(value = "/chat.html")
    public String index(Model model) {
        return INDEX;
    }

    /**
     * 问答
     *
     * @param question
     * @return
     */
    @ApiOperation(value = "问答")
    @PostMapping(value = "/chat/ask")
    @ResponseBody
    public ResponseDTO<AnswerDTO> ask(@Valid @RequestBody QuestionDTO question) {
        String answer = chatService.chat(question);
        return ResponseDTO.success(answer);
    }

}
