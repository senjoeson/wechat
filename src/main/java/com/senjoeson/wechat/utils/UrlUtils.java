package com.senjoeson.wechat.utils;

import java.net.URL;

/**
 * @author MyPC
 * @date 2018/11/29
 */

public class UrlUtils {

    /**
     * 全局拿到fxml
     *
     * @param xmlName fxml名称,如 main.xml
     * @return 返回它真实可用路径
     */
    public static URL parseUrl(String xmlName) {
        return UrlUtils.class.getClassLoader().getResource("../../../../fxml/" + xmlName);

    }

    /**
     * 全局拿到config配置文件
     *
     * @param profsName
     * @return
     */
    public static URL parseConfigUrl(String profsName) {
        return UrlUtils.class.getClassLoader().getResource("../../../../config/" + profsName);
    }


}
