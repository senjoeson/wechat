package com.senjoeson.wechat.wechat;

public class WeApi {


    /**
     * POST请求
     * data	URL Encode
     * arams	  appid: 应用ID
     * fun:      new 应用类型
     * lang:     zh_CN 语言
     * _:        时间戳
     */
    public static final String GET_UUID = "https://login.weixin.qq.com/jslogin";

    /**
     * GET 请求
     */
    public static final String GET_SCAN_CODE = "https://login.weixin.qq.com/qrcode/";


}
