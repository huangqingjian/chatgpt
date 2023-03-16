package com.chatgpt.service.impl;

import com.alibaba.fastjson.JSON;
import com.chatgpt.bean.CommonQueryBean;
import com.chatgpt.domain.UserRight;
import com.chatgpt.dto.CommonQueryDTO;
import com.chatgpt.dto.UserRightDTO;
import com.chatgpt.dto.PageDTO;
import com.chatgpt.exception.ServiceException;
import com.chatgpt.mapper.UserRightDetailMapper;
import com.chatgpt.mapper.UserRightMapper;
import com.chatgpt.service.UserRightService;
import com.chatgpt.util.BeanUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
* 用户权益服务
*
* author: huangqj
* date: 2022-11-20 11:27:16
*/
@Service
public class UserRightServiceImpl implements UserRightService {
    private static final Logger log = LoggerFactory.getLogger(UserRightServiceImpl.class);
    @Autowired
    private UserRightMapper userRightMapper;

    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageDTO<UserRightDTO> list(CommonQueryDTO queryDTO) {
        log.info("the {}.list parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(queryDTO));
        CommonQueryBean query = BeanUtils.map(queryDTO, CommonQueryBean.class);
        // 分页
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<UserRight> domains = userRightMapper.selectByQuery(query);
        PageInfo<UserRightDTO> pageInfo = new PageInfo(domains);
        // 转换为PageDTO
        PageDTO<UserRightDTO> page = BeanUtils.map(pageInfo, PageDTO.class);
        page.setList(BeanUtils.mapList(pageInfo.getList(), UserRightDTO.class));
        return page;
    }

    /**
     * 查找
     *
     * @param userId
     * @return
     */
    @Override
    public UserRightDTO findByUserId(Long userId) {
        log.info("the {}.findByUserId parameter: [{}]", this.getClass().getSimpleName(), userId);
        UserRight domain = userRightMapper.selectByUserId(userId);
        return BeanUtils.map(domain, UserRightDTO.class);
    }

    /**
     * 查找
     *
     * @param id
     * @return
     */
    @Override
    public UserRightDTO find(Long id) {
        log.info("the {}.find parameter: [{}]", this.getClass().getSimpleName(), id);
        UserRight domain = userRightMapper.selectByPrimaryKey(id);
        if(domain != null) {
            UserRightDTO dto = BeanUtils.map(domain, UserRightDTO.class);
            return dto;
        }
        return null;
    }

    /**
     * 新增
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public Long save(UserRightDTO dto) {
        log.info("the {}.save parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        UserRight domain = BeanUtils.map(dto, UserRight.class);
        userRightMapper.insertUserRight(domain);
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
    public Long update(UserRightDTO dto) {
        log.info("the {}.update parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        UserRight domain = userRightMapper.selectByPrimaryKey(dto.getId());
        Optional.ofNullable(domain).orElseThrow(() -> new ServiceException("数据非法～"));
        BeanUtils.copy(dto, domain, true);
        userRightMapper.updateUserRight(domain);
        return dto.getId();
    }

    /**
     * 扣减可用次数
     *
     * @param userId
     */
    @Override
    @Transactional
    public void reduceAvailableTimes(Long userId) {
        log.info("the {}.reduceAvailableTimes parameter: [{}]", this.getClass().getSimpleName(), userId);
        userRightMapper.reduceAvailableTimes(userId);
    }

}
