package com.senjoeson.wechat.wechat;

import com.senjoeson.wechat.task.CheckLoginStateTask;

public class WeChatCore {

    private static WeChatCore weChatCore;

    private boolean alive = false;


    private boolean checkScanState = false;


    public static WeChatCore getInstance() {
        if (weChatCore == null) {
            synchronized (WeChatCore.class) {
                if (weChatCore == null) {
                    weChatCore = new WeChatCore();
                }
            }
        }
        return weChatCore;
    }

    private WeChatCore() {
        //new Thread(()->)
        while (true) {
            if (checkScanState) {
                new Thread(new CheckLoginStateTask()).start();
            }
        }
    }


    public void updateAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean getCheckScanState() {
        return checkScanState;
    }

    /**
     * @param checkScanState
     */
    public void updateCheckScanState(boolean checkScanState) {
        this.checkScanState = checkScanState;
    }

    public void openCheckScanStateTask() {

    }


}
