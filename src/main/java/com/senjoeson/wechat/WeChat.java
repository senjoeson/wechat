package com.senjoeson.wechat;

import com.senjoeson.wechat.controller.LoginController;
import com.senjoeson.wechat.core.MsgCenter;
import com.senjoeson.wechat.face.IMsgHandlerFace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeChat {
    private static final Logger LOG = LoggerFactory.getLogger(WeChat.class);
    private IMsgHandlerFace msgHandler;

    public WeChat(IMsgHandlerFace msgHandler, String qrPath) {
        System.setProperty("jsse.enableSNIExtension", "false"); // 防止SSL错误
        this.msgHandler = msgHandler;

        // 登陆
        LoginController login = new LoginController();
        login.login(qrPath);
    }

    public void start() {
        LOG.info("+++++++++++++++++++开始消息处理+++++++++++++++++++++");
        new Thread(new Runnable() {
            @Override
            public void run() {
                MsgCenter.handleMsg(msgHandler);
            }
        }).start();
    }

}
