package com.chatgpt.web.controller;

import com.chatgpt.dto.PageDTO;
import com.chatgpt.dto.ResponseDTO;
import com.chatgpt.dto.UserDTO;
import com.chatgpt.dto.UserQueryDTO;
import com.chatgpt.enums.UserType;
import com.chatgpt.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 用户管理器
 *
 * author: huangqj
 * date: 2022-11-23 18:50:38
 */
@Api(tags = "用户管理器", value = "User.Manager")
@Controller
@RequestMapping(value="/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private static final String LIST = "/user/list";
    private static final String ADD = "/user/add";
    private static final String EDIT = "/user/edit";
    private static final String DETAIL = "/user/detail";

    @Value("${default.password:123456}")
    private String DEFAULT_PASSWORD;

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 用户列表页
     *
     * @return
     */
    @ApiOperation(value = "用户列表页")
    @RequestMapping(value = "list.html")
    public String list() {
        return LIST;
    }

    /**
     * 用户新增页
     *
     * @return
     */
    @ApiOperation(value = "用户新增页")
    @RequestMapping(value = "add.html")
    public String add() {
        return ADD;
    }

    /**
     * 用户编辑页
     *
     * @param id
     * @param model
     * @return
     */
    @ApiOperation(value = "用户编辑页")
    @RequestMapping(value = "/{id}.html")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return EDIT;
    }

    /**
     * 用户详情页
     *
     * @param id
     * @param model
     * @return
     */
    @ApiOperation(value = "用户详情页")
    @RequestMapping(value = "detail/{id}.html")
    public String detail(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        return DETAIL;
    }

    /**
     * 用户列表
     *
     * @param queryDTO
     * @return
     */
    @ApiOperation(value = "用户列表")
    @GetMapping(value = "list")
    @ResponseBody
    public ResponseDTO<PageDTO<UserDTO>> list(UserQueryDTO queryDTO) {
        queryDTO.setType(UserType.MANAGER.getValue());
        PageDTO<UserDTO> pageDTO = userService.list(queryDTO);
        return ResponseDTO.success(pageDTO);
    }

    /**
     * 用户查找
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "用户查找")
    @GetMapping(value = "get/{id}")
    @ResponseBody
    public ResponseDTO<UserDTO> get(@PathVariable("id") Long id) {
        UserDTO dto = userService.find(id);
        return ResponseDTO.success(dto);
    }

    /**
     * 用户新增
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "用户新增")
    @PostMapping(value = "save")
    @ResponseBody
    public ResponseDTO<Long> save(@RequestBody UserDTO dto) {
        dto.setType(UserType.MANAGER.getValue());
        Long id = userService.save(dto);
        return ResponseDTO.success(id);
    }

    /**
     * 用户更新
     *
     * @param dto
     * @return
     */
    @ApiOperation(value = "用户更新")
    @PostMapping(value = "update")
    @ResponseBody
    public ResponseDTO<Long> update(@RequestBody UserDTO dto) {
        Long id = userService.update(dto);
        return ResponseDTO.success(id);
    }

    /**
     * 用户重置密码
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "用户重置密码")
    @PostMapping(value = "/{id}/resetPassword")
    @ResponseBody
    public ResponseDTO<Boolean> resetPassword(@PathVariable("id") Long id) {
        String password = passwordEncoder.encode(DEFAULT_PASSWORD);
        userService.resetPassword(id, password);
        return ResponseDTO.success(true);
    }

    /**
     * 用户删除
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "用户删除")
    @PostMapping(value = "delete/{id}")
    @ResponseBody
    public ResponseDTO<Boolean> delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseDTO.success(Boolean.TRUE);
    }

}
