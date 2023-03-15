package com.chatgpt.service.impl;

import com.alibaba.fastjson.JSON;
import com.chatgpt.bean.CommonQueryBean;
import com.chatgpt.constant.Constant;
import com.chatgpt.context.UserContext;
import com.chatgpt.domain.Chat;
import com.chatgpt.dto.*;
import com.chatgpt.enums.ContentType;
import com.chatgpt.exception.ServiceException;
import com.chatgpt.listener.event.ChatEvent;
import com.chatgpt.mapper.ChatMapper;
import com.chatgpt.property.ChatGptProperty;
import com.chatgpt.service.ChatRecordService;
import com.chatgpt.service.ChatService;
import com.chatgpt.util.BeanUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* 聊天服务
*
* author: huangqj
* date: 2022-11-20 11:27:16
*/
@Service
public class ChatServiceImpl implements ChatService {
    private static final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);
    @Autowired
    private ChatGptProperty chatGptProperty;
    @Autowired
    private ChatMapper chatMapper;
    @Autowired
    private ChatRecordService chatRecordService;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageDTO<ChatDTO> list(CommonQueryDTO queryDTO) {
        log.info("the {}.list parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(queryDTO));
        CommonQueryBean query = BeanUtils.map(queryDTO, CommonQueryBean.class);
        // 分页
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<Chat> domains = chatMapper.selectByQuery(query);
        PageInfo<ChatDTO> pageInfo = new PageInfo(domains);
        // 转换为PageDTO
        PageDTO<ChatDTO> page = BeanUtils.map(pageInfo, PageDTO.class);
        page.setList(BeanUtils.mapList(pageInfo.getList(), ChatDTO.class));
        return page;
    }

    /**
     * 查找
     *
     * @param id
     * @return
     */
    @Override
    public ChatDTO find(Long id) {
        log.info("the {}.find parameter: [{}]", this.getClass().getSimpleName(), id);
        Chat domain = chatMapper.selectByPrimaryKey(id);
        if(domain != null) {
            ChatDTO dto = BeanUtils.map(domain, ChatDTO.class);
            return dto;
        }
        return null;
    }

    /**
     * 查找
     *
     * @param userId
     * @return
     */
    @Override
    public List<ChatDTO> listByUserId(Long userId) {
        log.info("the {}.listByUserId parameter: [{}]", this.getClass().getSimpleName(), userId);
        List<Chat> domain = chatMapper.selectByUserId(userId);
        List<ChatDTO> chats = BeanUtils.mapList(domain, ChatDTO.class);
        if(!CollectionUtils.isEmpty(chats)) {
            List<ChatRecordDTO> chatRecords = chatRecordService.listLastestByUserId(userId);
            Map<Long, ChatRecordDTO> chatRecordMap = chatRecords.stream().collect(Collectors.toMap(k -> k.getChatId(), v -> v, (k1, k2) -> k1));
            for(ChatDTO chat : chats) {
                chat.setLastest(chatRecordMap.get(chat.getId()));
            }
        }
        return chats;
    }

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public Long save(ChatDTO dto) {
        log.info("the {}.save parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        Chat domain = BeanUtils.map(dto, Chat.class);
        chatMapper.insertChat(domain);
        return domain.getId();
    }

    /**
     * 更新
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public Long update(ChatDTO dto) {
        log.info("the {}.update parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        Chat domain = chatMapper.selectByPrimaryKey(dto.getId());
        Optional.ofNullable(domain).orElseThrow(() -> new ServiceException("数据非法～"));
        BeanUtils.copy(dto, domain, true);
        chatMapper.updateChat(domain);
        return dto.getId();
    }

    /**
     * 删除
     *
     * @param chatId
     */
    @Override
    @Transactional
    public void delete(Long chatId) {
        log.info("the {}.delete parameter: [{}]", this.getClass().getSimpleName(), chatId);
        Chat chat = chatMapper.selectByPrimaryKey(chatId);
        Optional.ofNullable(chat).orElseThrow(() -> new ServiceException("会话不存在～"));
        //
        Long userId = UserContext.getUser();
        if(!Objects.equals(chat.getUserId(), userId)) {
            throw new ServiceException("您无权操作～");
        }
        chatMapper.deleteByPrimaryKey(chatId);
    }

    /**
     * 聊天
     *
     * @param question
     * @return
     */
    public AnswerDTO chat(QuestionDTO question) {
        log.info("the {}.chat parameter: [{}]", this.getClass().getSimpleName(), question);
        // todo 权限校验


        List<ContentDTO> contents = question.getContents();
        if(CollectionUtils.isEmpty(contents)) {
            throw new ServiceException("请输入您的问题～");
        }
        // 组装内容
        StringBuffer sb = new StringBuffer();
        if(Objects.equals(Boolean.TRUE, question.getCoherented())) {
            for (ContentDTO content : contents) {
                if (Objects.equals(ContentType.QUESTION.getValue(), content.getType())) {
                    sb.append(chatGptProperty.getQuestioner().concat(Constant.MAOHAO));
                } else if (Objects.equals(ContentType.ANSWER.getValue(), content.getType())) {
                    sb.append(chatGptProperty.getAnswer().concat(Constant.MAOHAO));
                }
                sb.append(content.getContent());
                sb.append(Constant.TNN);
            }
            sb.append(chatGptProperty.getAnswer().concat(Constant.MAOHAO));
        } else {
            sb.append(chatGptProperty.getQuestioner().concat(Constant.MAOHAO));
            sb.append(contents.get(contents.size() - 1).getContent());
            sb.append(Constant.TNN);
            sb.append(chatGptProperty.getAnswer().concat(Constant.MAOHAO));
        }
        // 请求chatgpt
        /**
        OpenAiService service = new OpenAiService(chatGptProperty.getToken(), Duration.ofSeconds(chatGptProperty.getRequestTimeout()));
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(sb.toString())
                .model(chatGptProperty.getModel())
                .temperature(chatGptProperty.getTemperature())
                .maxTokens(chatGptProperty.getMaxTokens())
                .build();
        // 响应结果处理
        List<CompletionChoice> completionChoices = service.createCompletion(completionRequest).getChoices();
        if(CollectionUtils.isEmpty(completionChoices)) {
            log.error("ChatGpt Answer:{}", JSON.toJSONString(completionChoices));
            throw new ServiceException("ChatGpt发生了意外～");
        }
        String answer = completionChoices.get(completionChoices.size() - 1).getText().replace(chatGptProperty.getAnswer().concat(Constant.MAOHAO), Constant.EMPTY);
        */
        String answer = "OK, my name is Limingqiang. I like singing, playing basketballand so on.";
        // 发布聊天事件
        applicationEventPublisher.publishEvent(new ChatEvent(this, question.getChatId(), contents.get(contents.size() - 1).getContent(), answer));
        return new AnswerDTO().chatId(question.getChatId()).content(answer);
    }


}
