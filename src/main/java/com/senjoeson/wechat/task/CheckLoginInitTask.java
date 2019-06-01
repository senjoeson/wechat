package com.senjoeson.wechat.task;

import com.senjoeson.wechat.api.WechatTools;
import com.senjoeson.wechat.core.Core;
import com.senjoeson.wechat.service.ILoginService;
import com.senjoeson.wechat.service.impl.LoginServiceImpl;
import com.senjoeson.wechat.utils.tools.CommonTools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

/**
 * 检查是否扫码的状态
 */
public class CheckLoginInitTask extends TimerTask {

    private static Logger LOG = LoggerFactory.getLogger(CheckLoginInitTask.class);
    private ILoginService loginService = new LoginServiceImpl();
    private Core core = Core.getInstance();


    @Override
    public void run() {

        if (core.getLoginState() && !core.isHasInit()) {  //已经登录


            LOG.info("4. 登陆超时，请重新扫描二维码图片");


            LOG.info("5. 登陆成功，微信初始化");
            if (!loginService.webWxInit()) {
                LOG.info("6. 微信初始化异常");
                System.exit(0);
            }

            LOG.info("6. 开启微信状态通知");
            loginService.wxStatusNotify();

            LOG.info("7. 清除。。。。");
            CommonTools.clearScreen();
            LOG.info(String.format("欢迎回来， %s", core.getNickName()));

            LOG.info("8. 开始接收消息");
            loginService.startReceiving();

            LOG.info("9. 获取联系人信息");
            loginService.webWxGetContact();

            LOG.info("10. 获取群好友及群好友列表");
            loginService.WebWxBatchGetContact();

            LOG.info("11. 缓存本次登陆好友相关消息");
            WechatTools.setUserInfo(); // 登陆成功后缓存本次登陆好友相关消息（NickName, UserName）

            LOG.info("12.开启微信状态检测线程");
            core.setHasInit(true);
        }
    }
}
