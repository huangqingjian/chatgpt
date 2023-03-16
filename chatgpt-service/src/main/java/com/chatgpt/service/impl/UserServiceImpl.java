package com.chatgpt.service.impl;

import com.alibaba.fastjson.JSON;
import com.chatgpt.bean.UserQueryBean;
import com.chatgpt.domain.User;
import com.chatgpt.dto.PageDTO;
import com.chatgpt.dto.PasswordDTO;
import com.chatgpt.dto.UserDTO;
import com.chatgpt.dto.UserQueryDTO;
import com.chatgpt.listener.event.UserAddEvent;
import com.chatgpt.mapper.UserMapper;
import com.chatgpt.service.UserService;
import com.chatgpt.util.BeanUtils;
import com.chatgpt.exception.ServiceException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
* 用户服务
*
* author: huangqj
* date: 2022-11-20 11:27:16
*/
@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 查询
     *
     * @param queryDTO
     * @return
     */
    @Override
    public PageDTO<UserDTO> list(UserQueryDTO queryDTO) {
        log.info("the {}.list parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(queryDTO));
        UserQueryBean query = BeanUtils.map(queryDTO, UserQueryBean.class);
        // 分页
        PageHelper.startPage(query.getPage(), query.getLimit());
        List<User> domains = userMapper.selectByQuery(query);
        PageInfo<UserDTO> pageInfo = new PageInfo(domains);
        // 转换为PageDTO
        PageDTO<UserDTO> page = BeanUtils.map(pageInfo, PageDTO.class);
        page.setList(BeanUtils.mapList(pageInfo.getList(), UserDTO.class));
        return page;
    }

    /**
     * 查询
     *
     * @param ids
     * @return
     */
    @Override
    public List<UserDTO> listByIds(List<Long> ids) {
        log.info("the {}.listByIds parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(ids));
        List<User> domains = userMapper.selectByPrimaryKeys(ids);
        return BeanUtils.mapList(domains, UserDTO.class);
    }

    /**
     * 查找
     *
     * @param id
     * @return
     */
    @Override
    public UserDTO find(Long id) {
        log.info("the {}.find parameter: [{}]", this.getClass().getSimpleName(), id);
        User domain = userMapper.selectByPrimaryKey(id);
        if(domain != null) {
            UserDTO dto = BeanUtils.map(domain, UserDTO.class);
            return dto;
        }
        return null;
    }

    /**
     * 查找
     *
     * @param mobile
     * @return
     */
    @Override
    public UserDTO findByMobile(String mobile) {
        log.info("the {}.findByMobile parameter: [{}]", this.getClass().getSimpleName(), mobile);
        User domain = userMapper.selectByMobile(mobile);
        if(domain != null) {
            UserDTO dto = BeanUtils.map(domain, UserDTO.class);
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
    public Long save(UserDTO dto) {
        log.info("the {}.save parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        User domain = BeanUtils.map(dto, User.class);
        userMapper.insertUser(domain);
        // 发布事件
        applicationEventPublisher.publishEvent(new UserAddEvent(this, domain.getId()));
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
    public Long update(UserDTO dto) {
        log.info("the {}.update parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
        User domain = userMapper.selectByPrimaryKey(dto.getId());
        Optional.ofNullable(domain).orElseThrow(() -> new ServiceException("数据非法～"));
        User mobileUser = userMapper.selectByMobile(dto.getMobile());
        if(mobileUser != null && !Objects.equals(mobileUser.getId(), domain.getId())) {
            throw new ServiceException(String.format("手机号【%s】已被占用～", dto.getMobile()));
        }
        BeanUtils.copy(dto, domain, true);
        userMapper.updateUser(domain);
        return dto.getId();
    }

    /**
     * 更新密码
     *
     * @param dto
     * @return
     */
    @Override
    @Transactional
    public void updatePassword(PasswordDTO dto) {
        log.info("the {}.updatePassword parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));

    }

//    /**
//     * 更新密码
//     *
//     * @param dto
//     * @return
//     */
//    @Override
//    @Transactional
//    public Long updatePassword(UserPasswordDTO dto) {
//        log.info("the {}.updatePassword parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
//        User domain = userMapper.selectByPrimaryKey(dto.getId());
//        Optional.ofNullable(domain).orElseThrow(() -> new ServiceException("数据非法～"));
//        if(!Objects.equals(domain.getPassword(), dto.getPassword())) {
//            throw new ServiceException("输入的密码不正确～");
//        }
//        if(!Objects.equals(dto.getNewPassword(), dto.getConfirmNewPassword())) {
//            throw new ServiceException("前后两次新密码不一致～");
//        }
//        domain.setPassword(dto.getPassword());
//        userMapper.updateUser(domain);
//        return dto.getId();
//    }

//    /**
//     * 注册
//     *
//     * @param dto
//     * @return
//     */
//    @Override
//    @Transactional
//    public Long register(UserRegisterDTO dto) {
//        log.info("the {}.register parameter: [{}]", this.getClass().getSimpleName(), JSON.toJSONString(dto));
//        User user = userMapper.selectByMobile(dto.getMobile());
//        if(user != null) {
//            throw new ServiceException("手机号已被占用～");
//        }
//        User domain = BeanUtils.map(dto, User.class);
//        userMapper.insertUser(domain);
//        return domain.getId();
//    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    @Transactional
    public void delete(Long id) {
        log.info("the {}.delete parameter: [{}]", this.getClass().getSimpleName(), id);
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 重置密码
     *
     * @param id
     * @param password
     */
    @Override
    @Transactional
    public void resetPassword(Long id, String password) {
        log.info("the {}.resetPassword parameter: [{} {}]", this.getClass().getSimpleName(), id, password);
        User user = userMapper.selectByPrimaryKey(id);
        Optional.ofNullable(user).orElseThrow(() -> new ServiceException("数据非法～"));
        user.setPassword(password);
        userMapper.updateUser(user);
    }

}
