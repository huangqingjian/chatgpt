package com.chatgpt.service.impl;

import com.alibaba.fastjson.JSON;
import com.chatgpt.ai.ChatCompletionMessage;
import com.chatgpt.ai.ChatGptService;
import com.chatgpt.ai.request.ChatCompletionRequest;
import com.chatgpt.ai.response.ChatCompletionChoice;
import com.chatgpt.ai.response.ChatCompletionResult;
import com.chatgpt.bean.CommonQueryBean;
import com.chatgpt.constant.Constant;
import com.chatgpt.context.UserContext;
import com.chatgpt.domain.Chat;
import com.chatgpt.dto.*;
import com.chatgpt.enums.AvailableUserRight;
import com.chatgpt.enums.ContentType;
import com.chatgpt.exception.AvailableException;
import com.chatgpt.exception.ServiceException;
import com.chatgpt.listener.event.ChatEvent;
import com.chatgpt.mapper.ChatMapper;
import com.chatgpt.property.ChatGptProperty;
import com.chatgpt.service.ChatRecordService;
import com.chatgpt.service.ChatService;
import com.chatgpt.service.UserRightService;
import com.chatgpt.util.BeanUtils;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.*;
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
    @Autowired
    private UserRightService userRightService;
    @Autowired
    private ChatGptService chatGptService;
    @Autowired
    @Qualifier(value = "chatCache")
    private LoadingCache<Long, ChatDTO> chatCache;

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
    public AnswerDTO chat(QuestionDTO question) throws Exception {
        log.info("the {}.chat parameter: [{}]", this.getClass().getSimpleName(), question);
        // 权限校验
        ChatDTO chat = chatCache.get(question.getChatId());
        Optional.ofNullable("会话不存在～");
        Long userId = UserContext.getUser();
        if(!Objects.equals(chat.getUserId(), userId)) {
            throw new ServiceException("您无权操作～");
        }
        List<ContentDTO> contents = question.getContents();
        if(CollectionUtils.isEmpty(contents)) {
            throw new ServiceException("请输入您的问题～");
        }
        // 获取用户可用权益
        AvailableUserRight availableUserRight = getAvailableUserRight(userId);
        // 聊天事件
        ChatEvent chatEvent = new ChatEvent(this)
            .chatId(question.getChatId())
            .question(contents.get(contents.size() - 1).getContent())
            .questionTime(new Date())
            .availableUserRight(availableUserRight);
        // 请求chatgpt4
        ChatGptDTO response = sendChatGpt4(contents, question.getCoherented());
        /**
        response.setAnswer("OK, my name is Limingqiang. I like singing, playing basketballand so on.");
         */
        if(response.getSuccessed()) {
            chatEvent.answer(response.getAnswer()).answerTime(new Date());
        }
        // 发布聊天事件
        applicationEventPublisher.publishEvent(chatEvent);
        if(!response.getSuccessed()) {
            throw new ServiceException("聊天机器人出问题啦～");
        }
        return new AnswerDTO().chatId(question.getChatId()).content(response.getAnswer());
    }

    /**
     * 发送给chatgpt 3
     *
     * @param contents
     * @param coherented
     * @return
     */
    private ChatGptDTO sendChatGpt3(List<ContentDTO> contents, Boolean coherented) {
        ChatGptDTO chatGpt = new ChatGptDTO();
        try {
            // 组装内容
            StringBuffer sb = new StringBuffer();
            if(Objects.equals(Boolean.TRUE, coherented)) {
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
            // 请求openai
            OpenAiService service = new OpenAiService(chatGptProperty.getToken(), Duration.ofSeconds(chatGptProperty.getRequestTimeout()));
            CompletionRequest completionRequest = CompletionRequest.builder()
                    .prompt(sb.toString())
                    .model(chatGptProperty.getModel_3())
                    .temperature(chatGptProperty.getTemperature())
                    .maxTokens(chatGptProperty.getMaxTokens())
                    .build();
            // 响应结果处理
            List<CompletionChoice> choices = service.createCompletion(completionRequest).getChoices();
            if (CollectionUtils.isEmpty(choices)) {
                log.error("ChatGpt3 Answer:{}", JSON.toJSONString(choices));
                chatGpt.setSuccessed(false);
            } else {
                String answer = choices.get(choices.size() - 1).getText().replace(chatGptProperty.getAnswer().concat(Constant.MAOHAO), Constant.EMPTY);
                chatGpt.setAnswer(answer);
            }
        } catch (Exception e) {
            log.error(String.format("ChatGpt3 Error: %s", e.getMessage()), e);
            chatGpt.setSuccessed(false);
        }
        return chatGpt;
    }

    /**
     * 发送给chatgpt 4
     *
     * @param contents
     * @param coherented
     * @return
     */
    private ChatGptDTO sendChatGpt4(List<ContentDTO> contents, Boolean coherented) {
        ChatGptDTO chatGpt = new ChatGptDTO();
        try {
            // 组装内容
            List<ChatCompletionMessage> messages = Lists.newArrayList();
            if(Objects.equals(Boolean.TRUE, coherented)) {
                for (ContentDTO content : contents) {
                    ChatCompletionMessage message = new ChatCompletionMessage();
                    if (Objects.equals(ContentType.QUESTION.getValue(), content.getType())) {
                        message.setRole(chatGptProperty.getQuestioner());
                    } else if (Objects.equals(ContentType.ANSWER.getValue(), content.getType())) {
                        message.setRole(chatGptProperty.getAnswer());
                    }
                    message.setContent(content.getContent());
                    messages.add(message);
                }
            } else {
                ChatCompletionMessage message = new ChatCompletionMessage();
                message.setRole(chatGptProperty.getQuestioner());
                message.setContent(contents.get(contents.size() - 1).getContent());
                messages.add(message);
            }
            // 请求openai
            ChatCompletionRequest completionRequest = new ChatCompletionRequest()
                    .messages(messages)
                    .model(chatGptProperty.getModel_4())
                    .temperature(chatGptProperty.getTemperature())
                    .max_tokens(chatGptProperty.getMaxTokens());
            ChatCompletionResult result = chatGptService.createChatCompletion(chatGptProperty.getToken(), chatGptProperty.getRequestTimeout(), completionRequest);
            // 响应结果处理
            List<ChatCompletionChoice> choices = result.getChoices();
            if (CollectionUtils.isEmpty(choices)) {
                log.error("ChatGpt4 Answer:{}", JSON.toJSONString(choices));
                chatGpt.setSuccessed(false);
            } else {
                String answer = choices.get(choices.size() - 1).getMessage().getContent();
                chatGpt.setAnswer(answer);
            }
        } catch (Exception e) {
            log.error(String.format("ChatGpt4 Error: %s", e.getMessage()), e);
            chatGpt.setSuccessed(false);
        }
        return chatGpt;
    }

    /**
     * 获取用户可用权益
     *
     * @param userId
     */
    private AvailableUserRight getAvailableUserRight(Long userId) {
        // 用户权益
        UserRightDTO userRight = userRightService.findByUserId(userId);
        Optional.ofNullable(userRight).orElseThrow(() -> new ServiceException("用户异常，请联系管理员～"));
        if(userRight.getStartAvailableTime() != null && userRight.getEndAvailableTime() != null) {
            if(userRight.getEndAvailableTime().after(new Date())) {
                return AvailableUserRight.PERIOD;
            } else {
                if(userRight.getAvailableTimes() > Constant.ZERO) {
                    return AvailableUserRight.TIMES;
                }
            }
        } else {
            if(userRight.getAvailableTimes() > Constant.ZERO) {
                return AvailableUserRight.TIMES;
            }
        }
        throw new AvailableException("您没有使用次数～");
     }



}
