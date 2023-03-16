package com.chatgpt.controller;

import com.chatgpt.domain.ChatRecord;
import com.chatgpt.dto.*;
import com.chatgpt.service.ChatRecordService;
import com.chatgpt.service.ChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private ChatRecordService chatRecordService;

    /**
     * 主页
     *
     * @param model
     * @return
     */
    @ApiOperation(value = "主页")
    @RequestMapping(value = { "", "/chat.html"})
    public String index(Model model) {
        return INDEX;
    }

    /**
     * 聊天列表
     *
     * @return
     */
    @ApiOperation(value = "聊天列表")
    @GetMapping(value = "/chat/list")
    @ResponseBody
    public ResponseDTO<List<ChatDTO>> list() {
        Long userId = getCurrentUserId();
        if(userId == null || userId == 0L) {
            return ResponseDTO.success(new ArrayList());
        }
        List<ChatDTO> chats = chatService.listByUserId(userId);
        return ResponseDTO.success(chats);
    }

    /**
     * 创建聊天
     *
     * @return
     */
    @ApiOperation(value = "问答")
    @PostMapping(value = "/chat/create")
    @ResponseBody
    public ResponseDTO<Long> create() {
        ChatDTO chat = new ChatDTO();
        chat.setUserId(getCurrentUserId());
        Long id = chatService.save(chat);
        return ResponseDTO.success(id);
    }

    /**
     * 删除聊天
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除聊天")
    @PostMapping(value = "/chat/delete/{id}")
    @ResponseBody
    public ResponseDTO<Boolean> delete(@PathVariable("id") Long id) {
        chatService.delete(id);
        return ResponseDTO.success(Boolean.TRUE);
    }

    /**
     * 聊天记录列表
     *
     * @param chatId
     * @return
     */
    @ApiOperation(value = "聊天记录列表")
    @GetMapping(value = "/chat/{chatId}/record")
    @ResponseBody
    public ResponseDTO<List<ChatRecordDTO>> listRecord(@PathVariable("chatId") Long chatId) {
        List<ChatRecordDTO> chats = chatRecordService.listByChatId(chatId);
        return ResponseDTO.success(chats);
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
    public ResponseDTO<AnswerDTO> ask(@Valid @RequestBody QuestionDTO question) throws Exception {
        AnswerDTO answer = chatService.chat(question);
        return ResponseDTO.success(answer);
    }

}
