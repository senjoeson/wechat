package com.senjoeson.wechat.base;

import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * @author MyPC
 * @date 2018/11/29
 */

public interface BaseController {


    void init(Stage stage, Parent parent);

    void initListener();

    void initData();
}
