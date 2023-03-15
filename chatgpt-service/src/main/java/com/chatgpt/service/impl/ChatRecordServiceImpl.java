package com.chatgpt.service.impl;

import com.alibaba.fastjson.JSON;
import com.chatgpt.bean.CommonQueryBean;
import com.chatgpt.context.UserContext;
import com.chatgpt.domain.ChatRecord;
import com.chatgpt.dto.*;
import com.chatgpt.exception.ServiceException;
import com.chatgpt.mapper.ChatRecordMapper;
import com.chatgpt.service.ChatRecordService;
import com.chatgpt.service.ChatService;
import com.chatgpt.util.BeanUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
* 聊天记录服务
*
* author: huangqj
* date: 2022-11-20 11:27:16
*/
@Service
public class ChatRecordServiceImpl implements ChatRecordService {
    private static final Logger log = LoggerFactory.getLogger(ChatRecordServiceImpl.class);
    @Autowired
    private ChatRecordMapper chatRecordMapper;
    @Autowired
    private ChatService chatService;

    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageDTO<ChatRecordDTO> list(CommonQueryDTO queryDTO) {
        log.info("the {}.list parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(queryDTO));
        CommonQueryBean query = BeanUtils.map(queryDTO, CommonQueryBean.class);
        // 分页
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<ChatRecord> domains = chatRecordMapper.selectByQuery(query);
        PageInfo<ChatRecordDTO> pageInfo = new PageInfo(domains);
        // 转换为PageDTO
        PageDTO<ChatRecordDTO> page = BeanUtils.map(pageInfo, PageDTO.class);
        page.setList(BeanUtils.mapList(pageInfo.getList(), ChatRecordDTO.class));
        return page;
    }

    /**
     * 查找
     *
     * @param id
     * @return
     */
    @Override
    public ChatRecordDTO find(Long id) {
        log.info("the {}.find parameter: [{}]", this.getClass().getSimpleName(), id);
        ChatRecord domain = chatRecordMapper.selectByPrimaryKey(id);
        if(domain != null) {
            ChatRecordDTO dto = BeanUtils.map(domain, ChatRecordDTO.class);
            return dto;
        }
        return null;
    }

    /**
     * 查找
     *
     * @param chatId
     * @return
     */
    @Override
    public List<ChatRecordDTO> listByChatId(Long chatId) {
        log.info("the {}.listByChatId parameter: [{}]", this.getClass().getSimpleName(), chatId);
        ChatDTO chat = chatService.find(chatId);
        Optional.ofNullable(chat).orElseThrow(() -> new ServiceException("会话不存在～"));
        Long userId = UserContext.getUser();
        if(!Objects.equals(chat.getUserId(), userId)) {
            throw new ServiceException("您无权操作～");
        }
        List<ChatRecord> domain = chatRecordMapper.selectByChatId(chatId);
        return BeanUtils.mapList(domain, ChatRecordDTO.class);
    }

    /**
     * 查找用户最新
     *
     * @param userId
     * @return
     */
    @Override
    public List<ChatRecordDTO> listLastestByUserId(Long userId) {
        log.info("the {}.listLastestByUserId parameter: [{}]", this.getClass().getSimpleName(), userId);
        List<ChatRecord> domain = chatRecordMapper.selectLastestByUserId(userId);
        return BeanUtils.mapList(domain, ChatRecordDTO.class);
    }

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public Long save(ChatRecordDTO dto) {
        log.info("the {}.save parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        ChatRecord domain = BeanUtils.map(dto, ChatRecord.class);
        chatRecordMapper.insertChatRecord(domain);
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
    public Long update(ChatRecordDTO dto) {
        log.info("the {}.update parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        ChatRecord domain = chatRecordMapper.selectByPrimaryKey(dto.getId());
        Optional.ofNullable(domain).orElseThrow(() -> new ServiceException("数据非法～"));
        BeanUtils.copy(dto, domain, true);
        chatRecordMapper.updateChatRecord(domain);
        return dto.getId();
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    @Transactional
    public void delete(Long id) {
        log.info("the {}.delete parameter: [{}]", this.getClass().getSimpleName(), id);
        chatRecordMapper.deleteByPrimaryKey(id);
    }

}
