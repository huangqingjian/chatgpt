package com.chatgpt.service.impl;

import com.alibaba.fastjson.JSON;
import com.chatgpt.bean.CommonQueryBean;
import com.chatgpt.domain.UserRightDetail;
import com.chatgpt.dto.CommonQueryDTO;
import com.chatgpt.dto.PageDTO;
import com.chatgpt.dto.UserRightDetailDTO;
import com.chatgpt.exception.ServiceException;
import com.chatgpt.mapper.UserRightDetailMapper;
import com.chatgpt.service.UserRightDetailService;
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
* 用户权益明细服务
*
* author: huangqj
* date: 2022-11-20 11:27:16
*/
@Service
public class UserRightDetailServiceImpl implements UserRightDetailService {
    private static final Logger log = LoggerFactory.getLogger(UserRightDetailServiceImpl.class);
    @Autowired
    private UserRightDetailMapper userRightDetailMapper;

    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageDTO<UserRightDetailDTO> list(CommonQueryDTO queryDTO) {
        log.info("the {}.list parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(queryDTO));
        CommonQueryBean query = BeanUtils.map(queryDTO, CommonQueryBean.class);
        // 分页
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<UserRightDetail> domains = userRightDetailMapper.selectByQuery(query);
        PageInfo<UserRightDetailDTO> pageInfo = new PageInfo(domains);
        // 转换为PageDTO
        PageDTO<UserRightDetailDTO> page = BeanUtils.map(pageInfo, PageDTO.class);
        page.setList(BeanUtils.mapList(pageInfo.getList(), UserRightDetailDTO.class));
        return page;
    }

    /**
     * 查询
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserRightDetailDTO> listByRightId(Long userId) {
        log.info("the {}.findByUserId parameter: [{}]", this.getClass().getSimpleName(), userId);
        List<UserRightDetail> domain = userRightDetailMapper.selectByRightId(userId);
        return BeanUtils.mapList(domain, UserRightDetailDTO.class);
    }

    /**
     * 查找
     *
     * @param id
     * @return
     */
    @Override
    public UserRightDetailDTO find(Long id) {
        log.info("the {}.find parameter: [{}]", this.getClass().getSimpleName(), id);
        UserRightDetail domain = userRightDetailMapper.selectByPrimaryKey(id);
        if(domain != null) {
            UserRightDetailDTO dto = BeanUtils.map(domain, UserRightDetailDTO.class);
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
    public Long save(UserRightDetailDTO dto) {
        log.info("the {}.save parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        UserRightDetail domain = BeanUtils.map(dto, UserRightDetail.class);
        userRightDetailMapper.insertUserRightDetail(domain);
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
    public Long update(UserRightDetailDTO dto) {
        log.info("the {}.update parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        UserRightDetail domain = userRightDetailMapper.selectByPrimaryKey(dto.getId());
        Optional.ofNullable(domain).orElseThrow(() -> new ServiceException("数据非法～"));
        BeanUtils.copy(dto, domain, true);
        userRightDetailMapper.updateUserRightDetail(domain);
        return dto.getId();
    }

}
