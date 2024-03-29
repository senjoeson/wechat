package com.senjoeson.wechat.demo.demo1;

import com.senjoeson.wechat.Wechat;
import com.senjoeson.wechat.face.IMsgHandlerFace;

import java.io.File;

/**
 * @author https://github.com/yaphone
 * @version 1.0
 * @date 创建时间：2017年4月28日 上午12:44:10
 */
public class MyTest {
    public static void main(String[] args) {
        String qrPath = "D://itchat4j//login"; // 保存登陆二维码图片的路径，这里需要在本地新建目录
        File file = new File(qrPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        IMsgHandlerFace msgHandler = new SimpleDemo(); // 实现IMsgHandlerFace接口的类
        Wechat wechat = new Wechat(msgHandler, qrPath); // 【注入】
        wechat.start(); // 启动服务，会在qrPath下生成一张二维码图片，扫描即可登陆，注意，二维码图片如果超过一定时间未扫描会过期，过期时会自动更新，所以你可能需要重新打开图片
    }
}
