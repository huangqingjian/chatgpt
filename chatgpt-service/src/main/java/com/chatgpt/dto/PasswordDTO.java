package com.chatgpt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
* 密码 DTO
*
* author: huangqj
* date: 2022-11-10 10:43:48
*/
@ApiModel("密码DTO")
public class PasswordDTO {
    /**
     * 旧密码
     */
    @ApiModelProperty("旧密码")
    @NotEmpty(message = "旧密码不能为空")
    private String password;

    /**
     * 新密码
     */
    @ApiModelProperty("新密码")
    @NotEmpty(message = "新密码不能为空")
    private String newPassword;

    /**
     * 确认密码
     */
    @ApiModelProperty("确认密码")
    @NotEmpty(message = "确认密码不能为空")
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}