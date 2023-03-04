package com.chatgpt.context;

/**
 * 用户上下文
 */
public class UserContext {
    private final static ThreadLocal<Long> USER = ThreadLocal.withInitial(() -> 0L);

    /**
     * 设置用户
     *
     * @param userId
     */
    public static void setUser(Long userId){
        USER.set(userId);
    }

    /**
     * 获取用户
     *
     * @return
     */
    public static Long getUser(){
        return USER.get();
    }

    /**
     * 移除用户
     */
    public static void remove(){
        USER.remove();
    }
}
