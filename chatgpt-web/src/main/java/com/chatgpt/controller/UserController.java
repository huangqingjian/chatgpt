package com.chatgpt.controller;

import com.alibaba.fastjson.JSON;
import com.chatgpt.configuration.secure.UserDetailsDTO;
import com.chatgpt.constant.Constant;
import com.chatgpt.dto.PasswordDTO;
import com.chatgpt.dto.ResponseDTO;
import com.chatgpt.dto.UserDTO;
import com.chatgpt.exception.ServiceException;
import com.chatgpt.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.security.sasl.AuthenticationException;
import javax.validation.Valid;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 用户管理器
 *
 * author: huangqj
 * date: 2022-11-20 11:27:16
 */
@Api(tags = "用户管理器", value = "User.Manager")
@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController{
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private static final String LOGIN = "/user/login";
    private static final String REGISTER = "/user/register";

    private static final String CHANGE_PASSWORD = "/user/change-password";
    private static final String DASHBOARD = "/user/dashboard";
    private static final String PROFILE = "/user/profile";

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登录页
     *
     * @param model
     * @return
     */
    @ApiOperation(value = "登录页")
    @RequestMapping(value = "/login.html")
    public String loginPage(Model model) {
        return LOGIN;
    }

    /**
     * 注册页
     *
     * @param model
     * @return
     */
    @ApiOperation(value = "注册页")
    @RequestMapping(value = "/register.html")
    public String registerPage(Model model) {
        return REGISTER;
    }

    /**
     * 注册
     *
     * @return
     */
    @ApiOperation(value = "注册")
    @PostMapping(value = "/register")
    @ResponseBody
    public ResponseDTO<Boolean> register(@RequestBody @Valid UserDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        userService.save(dto);
        return ResponseDTO.success(true);
    }

    /**
     * 资料页
     *
     * @return
     */
    @ApiOperation(value = "资料页")
    @GetMapping(value = "/profile.html")
    public String profile() {
        return PROFILE;
    }

    /**
     * 修改密码页
     *
     * @param model
     * @return
     */
    @ApiOperation(value = "修改密码页")
    @RequestMapping(value = "/change-password.html")
    public String changePassword(Model model) {
        return CHANGE_PASSWORD;
    }

    /**
     * 个人面板页
     *
     * @return
     */
    @ApiOperation(value = "个人面板页")
    @RequestMapping(value = "/dashboard.html")
    public String dashboard() {
        return DASHBOARD;
    }


    /**
     * 获取当前用户
     *
     * @return
     */
    @ApiOperation(value = "获取当前用户")
    @GetMapping(value = "/getCurrent")
    @ResponseBody
    public ResponseDTO<UserDetailsDTO> getCurrent() {
        UserDetailsDTO currentUser = getCurrentUser();
        return ResponseDTO.success(currentUser);
    }

    /**
     * 更新当前用户
     *
     * @return
     */
    @ApiOperation(value = "更新当前用户")
    @PostMapping(value = "/update")
    @ResponseBody
    public ResponseDTO<Boolean> update(@RequestBody @Valid UserDTO userDTO) throws Exception{
        Long userId = getCurrentUserId();
        userDTO.setId(userId);
        userService.update(userDTO);
        // 更新缓存
        String userStr = stringRedisTemplate.opsForValue().get(Constant.WEB_SESSION_PREFIX + userId);
        UserDetailsDTO userDetails = JSON.parseObject(userStr, UserDetailsDTO.class);
        if(userDetails == null) {
            throw new AuthenticationException("请登录后访问～");
        }
        userDetails.setFace(userDTO.getFace());
        userDetails.setName(userDTO.getName());
        userDetails.setMobile(userDTO.getMobile());
        String cacheKey = Constant.WEB_SESSION_PREFIX + userId;
        stringRedisTemplate.opsForValue().set(cacheKey, JSON.toJSONString(userDetails), Constant.SESSION_EXPIRATION, TimeUnit.SECONDS);
        return ResponseDTO.success(Boolean.TRUE);
    }

    /**
     * 更新密码
     *
     * @param passwordDTO
     * @return
     */
    @ApiOperation(value = "更新密码")
    @PostMapping(value = "/updatePassword")
    @ResponseBody
    public ResponseDTO<Boolean> updatePassword(@RequestBody @Valid PasswordDTO passwordDTO) {
        Long userId = getCurrentUserId();
        UserDTO user = userService.find(userId);
        if(!passwordEncoder.matches(passwordDTO.getPassword(), user.getPassword())) {
            throw new ServiceException("输入的旧密码错误～");
        }
        if(!Objects.equals(passwordDTO.getNewPassword(), passwordDTO.getConfirmPassword())) {
            throw new ServiceException("前后两次输入的新密码不一致～");
        }
        UserDTO dto = new UserDTO();
        dto.setId(userId);
        dto.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
        userService.update(dto);
        return ResponseDTO.success(Boolean.TRUE);
    }

    /**
     * 添加RequestURI
     *
     * @param model
     */
    @ModelAttribute
    private void before(Model model) {
        model.addAttribute("currentUser", getCurrentUser());
    }
}
