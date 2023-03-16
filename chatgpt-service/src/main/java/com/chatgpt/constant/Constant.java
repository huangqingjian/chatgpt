package com.chatgpt.constant;

/**
 * 常量
 */
public class Constant {
    public static final String ID = "_id";
    public static final String RESULT = "_result";
    public static final String CURRENT = "_current";

    public static final String MAOHAO = ":";
    public static final String CLUSTER_NAME = "cluster.name";
    public static final String CLIENT_TRANSPORT_SNIFF = "client.transport.sniff";
    public static final String CLIENT_TRANSPORT_IGNORE_CLUSTER_NAME = "client.transport.ignore_cluster_name";

    public static final int PAGE_NUM = 1;
    public static final int PAGE_SIZE = 20;

    public static final String USER_PREFIX = "user:";
    public static final String person_TOKEN = "person_token";
    public static final String person_SESSION_ID = "person_session_id";
    public static final String TOKEN_INVALID = "token is invalid, please login again.";

    public static final String INDEX_TYPE = "_doc";

    public static final int DEFAULT_SHARDS = 1;
    public static final int DEFAULT_REPLICAS = 1;

    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String DOUHAO = ",";

    public static final String EMPTY = "";
    public static final String ZX = "-";
    public static final String POINT = ".";
    public static final String XG = "/";
    public static final String DY = "=";
    public static final String AND = "&";
    public static final String TNN = "\n\n";

    public static final String ALL_NOVEL = "全部小说";
    /**
     * 时分
     */
    public static final String HH_MM = "HH:mm";
    /**
     * 图片
     */
    public static final String PICTURE = "picture";
    /**
     * -1
     */
    public static final int NEGATIVE_ONE = -1;
    /**
     * 0
     */
    public static final int ZERO = 0;
    /**
     * 1
     */
    public static final int ONE = 1;
    /**
     * 2
     */
    public static final int TWO = 2;
    /**
     * 7
     */
    public static final int SEVEN = 7;
    /**
     * 8
     */
    public static final int EIGHT = 8;

    public static final String ADMIN_PREFIX = "admin:";
    public static final String WEB_PREFIX = "web:";

    /**
     * admin session
     */
    public static final String ADMIN_SESSION = "admin.session";
    /**
     * manage session
     */
    public static final String MANAGE_SESSION = "manage.session";
    /**
     * web session
     */
    public static final String WEB_SESSION = "web.session";

    public static final String VISIT_INVALID = "非法访问～";
    public static final String SESSION_INVALID = "会话已过期，请重新登陆～";
    public static final String NOPERMIT = "无访问权限～";

    public static final String UTF_8 = "UTF-8";
    public static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";

    public static final long SESSION_EXPIRATION = 86400L;

    public static final String VIP_RIGHT = "会员权益截止日期%s";
    public static final String NORMAL_RIGHT = "普通用户";

    /**
     * admin prefix
     */
    public static final String ADMIN_SESSION_PREFIX = "admin:session:";
    public static final String ADMIN_CAPTCHA_PREFIX = "admin:captcha:";

    /**
     * manage prefix
     */
    public static final String MANAGE_SESSION_PREFIX = "manage:session:";
    public static final String MANAGE_CAPTCHA_PREFIX = "manage:captcha:";

    /**
     * web prefix
     */
    public static final String WEB_SESSION_PREFIX = "web:session:";
    public static final String WEB_CAPTCHA_PREFIX = "web:captcha:";

    /**
     * 头
     */
    public static class Header {
        public static final String X_REQUESTED_WITH = "X-Requested-With";
        public static final String XML_HTTP_REQUEST = "XMLHttpRequest";
        public static final String CONTENT_TYPE = "Content-type";
        public static final String UA = "User-Agent";
    }
}
