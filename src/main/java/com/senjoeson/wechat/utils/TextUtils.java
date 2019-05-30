package com.senjoeson.wechat.utils;

/**
 * @author MyPC
 * @date 2018/11/29
 */

public class TextUtils {

    /**
     * 字符串是否为空
     * @param content
     * @return
     */
    public static boolean isEmpty(String content) {
        return !(content != null && content.length() > 0);
    }

    /**
     *  获取后缀名
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return suffix;
    }
}
