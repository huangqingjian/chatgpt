package com.chatgpt.configuration.secure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 系统权限配置类
 */
@ConfigurationProperties("security")
public class SecurityProperty {
    /**
     * 超级用户不认证
     */
    private boolean superAuthOpen;

    /**
     * 不验证权限用户名
     */
    private String superAdmin;

    /**
     * 记住密码标识
     */
    private String rememberKey;

    /**
     * 开放接口列表
     */
    private String[] openApi;

    /**
     * 是否允许多账号在线
     */
    private Integer maximum = 1;

    public boolean isSuperAuthOpen() {
        return superAuthOpen;
    }

    public void setSuperAuthOpen(boolean superAuthOpen) {
        this.superAuthOpen = superAuthOpen;
    }

    public String getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(String superAdmin) {
        this.superAdmin = superAdmin;
    }

    public String getRememberKey() {
        return rememberKey;
    }

    public void setRememberKey(String rememberKey) {
        this.rememberKey = rememberKey;
    }

    public String[] getOpenApi() {
        return openApi;
    }

    public void setOpenApi(String[] openApi) {
        this.openApi = openApi;
    }

    public Integer getMaximum() {
        return maximum;
    }

    public void setMaximum(Integer maximum) {
        this.maximum = maximum;
    }
}
