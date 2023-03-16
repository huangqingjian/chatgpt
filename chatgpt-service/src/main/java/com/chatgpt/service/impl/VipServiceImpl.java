package com.chatgpt.service.impl;

import com.alibaba.fastjson.JSON;
import com.chatgpt.bean.CommonQueryBean;
import com.chatgpt.constant.Constant;
import com.chatgpt.context.UserContext;
import com.chatgpt.domain.Vip;
import com.chatgpt.dto.*;
import com.chatgpt.enums.PayStatus;
import com.chatgpt.enums.UserRightSource;
import com.chatgpt.enums.VipPeriod;
import com.chatgpt.exception.ServiceException;
import com.chatgpt.mapper.VipMapper;
import com.chatgpt.service.*;
import com.chatgpt.util.BeanUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Vip服务
 *
 * author: huangqj
 * date: 2022-11-20 11:27:16
 */
@Service
public class VipServiceImpl implements VipService {
    private static final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);
    @Autowired
    private UserRightService userRightService;
    @Autowired
    private UserRightDetailService userRightDetailService;
    @Autowired
    private CostService costService;
    @Autowired
    private VipMapper vipMapper;

    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageDTO<VipDTO> list(CommonQueryDTO queryDTO) {
        log.info("the {}.list parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(queryDTO));
        CommonQueryBean query = BeanUtils.map(queryDTO, CommonQueryBean.class);
        // 分页
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<Vip> domains = vipMapper.selectByQuery(query);
        PageInfo<VipDTO> pageInfo = new PageInfo(domains);
        // 转换为PageDTO
        PageDTO<VipDTO> page = BeanUtils.map(pageInfo, PageDTO.class);
        page.setList(BeanUtils.mapList(pageInfo.getList(), VipDTO.class));
        return page;
    }

    /**
     * 查询
     *
     * @return
     */
    @Override
    public List<VipDTO> listAll() {
        log.info("the {}.listAll parameter: []", this.getClass().getSimpleName());
        List<Vip> domains = vipMapper.selectByQuery(new CommonQueryBean());
        return BeanUtils.mapList(domains, VipDTO.class);
    }

    /**
     * 查找
     *
     * @param id
     * @return
     */
    @Override
    public VipDTO find(Long id) {
        log.info("the {}.find parameter: [{}]", this.getClass().getSimpleName(), id);
        Vip domain = vipMapper.selectByPrimaryKey(id);
        if(domain != null) {
            VipDTO dto = BeanUtils.map(domain, VipDTO.class);
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
    public Long save(VipDTO dto) {
        log.info("the {}.save parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        Vip domain = BeanUtils.map(dto, Vip.class);
        if(domain.getTimes() == null) {
            domain.setTimes(Constant.NEGATIVE_ONE);
        }
        vipMapper.insertProduct(domain);
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
    public Long update(VipDTO dto) {
        log.info("the {}.update parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        Vip domain = vipMapper.selectByPrimaryKey(dto.getId());
        Optional.ofNullable(domain).orElseThrow(() -> new ServiceException("数据非法～"));
        BeanUtils.copy(dto, domain, true);
        vipMapper.updateProduct(domain);
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
        vipMapper.deleteByPrimaryKey(id);
    }

    /**
     * vip购买
     *
     * @param vipBuy
     */
    @Override
    @Transactional
    public void buy(VipBuyDTO vipBuy) {
        log.info("the {}.buy parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(vipBuy));
        // 会员校验
        Vip vip = vipMapper.selectByPrimaryKey(vipBuy.getVipId());
        Optional.ofNullable(vip).orElseThrow(() -> new ServiceException("购买的服务非法～"));
        Long userId = UserContext.getUser();
        // 生成消费记录 FIXME 假设支付成功
        CostDTO cost = new CostDTO();
        cost.setUserId(userId);
        cost.setProductId(vipBuy.getVipId());
        cost.setAmount(vip.getPrice());
        cost.setOrderTime(new Date());
        cost.setPayTime(new Date());
        cost.setPayStatus(PayStatus.PAYED.getValue());
        costService.save(cost);
        // 发放会员权益
        UserRightDTO userRight = userRightService.findByUserId(userId);
        Optional.ofNullable(userRight).orElseThrow(() -> new ServiceException("用户异常，请联系管理员～"));
        Calendar calendar = Calendar.getInstance();
        Date startAvailableTime = userRight.getStartAvailableTime();
        Date endAvailableTime = userRight.getEndAvailableTime();
        // 会员时间累加
        if(startAvailableTime == null || endAvailableTime == null) {
            startAvailableTime = calendar.getTime();
            addCalendarPeriod(calendar, vip.getPeriod());
            endAvailableTime = calendar.getTime();
        } else if (endAvailableTime.before(calendar.getTime())) {
            startAvailableTime = calendar.getTime();
            addCalendarPeriod(calendar, vip.getPeriod());
            endAvailableTime = calendar.getTime();
        } else {
            calendar.setTime(endAvailableTime);
            addCalendarPeriod(calendar, vip.getPeriod());
            endAvailableTime = calendar.getTime();
        }
        userRight.setStartAvailableTime(startAvailableTime);
        userRight.setEndAvailableTime(endAvailableTime);
        userRightService.update(userRight);
        // 权益变更明细
        UserRightDetailDTO userRightDetail = new UserRightDetailDTO();
        userRightDetail.setRightId(userRight.getId());
        userRightDetail.setDesc(String.format("购买%s，会员时间增加1%s", vip.getName(), VipPeriod.getProductPeriod(vip.getPeriod())));
        userRightDetail.setSource(UserRightSource.USER.getValue());
        userRightDetailService.save(userRightDetail);
    }

    /**
     *
     *
     * @param calendar
     * @param period
     */
    public void addCalendarPeriod(Calendar calendar, Integer period) {
        if(Objects.equals(VipPeriod.YEAR.getValue(), period)) {
            calendar.add(Calendar.YEAR, Constant.ONE);
        } else if(Objects.equals(VipPeriod.MONTH.getValue(), period)) {
            calendar.add(Calendar.MONTH, Constant.ONE);
        } else {
            calendar.add(Calendar.DATE, Constant.SEVEN);
        }
    }

}
