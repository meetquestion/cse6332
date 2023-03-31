package com.bupt.uta.common;

/**
 * 基于ThreadLocal封装工具类，用户保存和获取当前登录用户id
 */
public class BaseContext {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 设置值
     * @param username
     */
    public static void setCurrentUsername(String username){
        threadLocal.set(username);
    }

    /**
     * 获取值
     * @return
     */
    public static String getCurrentUsername(){
        return threadLocal.get();
    }
}
