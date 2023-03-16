package com.chatgpt.service.impl;

import com.alibaba.fastjson.JSON;
import com.chatgpt.bean.CommonQueryBean;
import com.chatgpt.domain.Cost;
import com.chatgpt.dto.CostDTO;
import com.chatgpt.dto.CommonQueryDTO;
import com.chatgpt.dto.PageDTO;
import com.chatgpt.exception.ServiceException;
import com.chatgpt.mapper.CostMapper;
import com.chatgpt.service.CostService;
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
* 消费服务
*
* author: huangqj
* date: 2022-11-20 11:27:16
*/
@Service
public class CostServiceImpl implements CostService {
    private static final Logger log = LoggerFactory.getLogger(CostServiceImpl.class);
    @Autowired
    private CostMapper costMapper;

    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageDTO<CostDTO> list(CommonQueryDTO queryDTO) {
        log.info("the {}.list parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(queryDTO));
        CommonQueryBean query = BeanUtils.map(queryDTO, CommonQueryBean.class);
        // 分页
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<Cost> domains = costMapper.selectByQuery(query);
        PageInfo<CostDTO> pageInfo = new PageInfo(domains);
        // 转换为PageDTO
        PageDTO<CostDTO> page = BeanUtils.map(pageInfo, PageDTO.class);
        page.setList(BeanUtils.mapList(pageInfo.getList(), CostDTO.class));
        return page;
    }

    /**
     * 查询
     *
     * @param userId
     * @return
     */
    @Override
    public List<CostDTO> listByUserId(Long userId) {
        log.info("the {}.listByUserId parameter: [{}]", this.getClass().getSimpleName(), userId);
        List<Cost> domains = costMapper.selectByUserId(userId);
        return BeanUtils.mapList(domains, CostDTO.class);
    }

    /**
     * 查找
     *
     * @param id
     * @return
     */
    @Override
    public CostDTO find(Long id) {
        log.info("the {}.find parameter: [{}]", this.getClass().getSimpleName(), id);
        Cost domain = costMapper.selectByPrimaryKey(id);
        if(domain != null) {
            CostDTO dto = BeanUtils.map(domain, CostDTO.class);
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
    public Long save(CostDTO dto) {
        log.info("the {}.save parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        Cost domain = BeanUtils.map(dto, Cost.class);
        costMapper.insertCost(domain);
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
    public Long update(CostDTO dto) {
        log.info("the {}.update parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        Cost domain = costMapper.selectByPrimaryKey(dto.getId());
        Optional.ofNullable(domain).orElseThrow(() -> new ServiceException("数据非法～"));
        BeanUtils.copy(dto, domain, true);
        costMapper.updateCost(domain);
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
        costMapper.deleteByPrimaryKey(id);
    }

}
