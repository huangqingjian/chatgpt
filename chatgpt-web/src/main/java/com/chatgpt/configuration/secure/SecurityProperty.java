package com.chatgpt.configuration.secure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 系统权限配置类
 */
@ConfigurationProperties("security")
public class SecurityProperty {

    /**
     * 开放接口列表
     */
    private String[] openApi;
    /**
     * 静态资源
     */
    private String[] staticUrl;

    public String[] getOpenApi() {
        return openApi;
    }

    public void setOpenApi(String[] openApi) {
        this.openApi = openApi;
    }

    public String[] getStaticUrl() {
        return staticUrl;
    }

    public void setStaticUrl(String[] staticUrl) {
        this.staticUrl = staticUrl;
    }
}
