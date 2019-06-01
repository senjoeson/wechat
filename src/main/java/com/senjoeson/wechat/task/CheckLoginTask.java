package com.senjoeson.wechat.task;

import com.senjoeson.wechat.service.ILoginService;
import com.senjoeson.wechat.service.impl.LoginServiceImpl;

import java.util.TimerTask;

public class CheckLoginTask extends TimerTask {


    private ILoginService loginService = new LoginServiceImpl();

    @Override
    public void run() {
        loginService.login();
    }


}
