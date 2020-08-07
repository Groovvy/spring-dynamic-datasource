package com.gitee.groovvy.config;

/**
 * @author wanghuaan
 * @date 2020/7/21
 **/
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "db0";
        }
    };


    public static void setDataSourceKey(String key) {
        contextHolder.set(key);
    }


    public static String getDataSourceKey() {
        return contextHolder.get();
    }


    public static void clearDataSourceKey() {
        contextHolder.remove();
    }

}
