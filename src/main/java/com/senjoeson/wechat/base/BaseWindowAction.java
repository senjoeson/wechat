package com.senjoeson.wechat.base;

import javafx.stage.Stage;

/**
 * 窗体管理器的通用方法
 */
public interface BaseWindowAction {


    void initPage(Stage stage);

    void initData();

    void initListener();
}
