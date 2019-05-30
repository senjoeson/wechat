package com.senjoeson.wechat.task;

/**
 * 检查是否扫码的状态
 */
public class CheckLoginStateTask implements Runnable {


    @Override
    public void run() {
        System.out.println("我正在检查登录的状态...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
