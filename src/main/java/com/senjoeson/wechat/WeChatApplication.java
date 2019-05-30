package com.senjoeson.wechat;

import cn.hutool.http.HttpUtil;
import com.senjoeson.wechat.base.BaseApplication;
import com.senjoeson.wechat.controller.WeChatController;
import com.senjoeson.wechat.wechat.WeApi;
import com.senjoeson.wechat.wechat.WeChatCore;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.InputStream;

public class WeChatApplication extends BaseApplication<WeChatController> {
    @Override
    protected String setFxmlResourceName() {
        return "wechat_root.fxml";
    }

    // long downloadFile = HttpUtil.downloadFile(WeApi.GET_SCAN_CODE + "4clfc3wWvg==", "C:\\Users\\kayakAndroid\\Desktop\\wechat\\login\\login.png");

    @Override
    protected void initPage(FXMLLoader fxmlLoader, Stage primaryStage, Parent parent) {

        WeChatController weChatController = fxmlLoader.getController();
        InputStream inputStream = HttpUtil.createGet(WeApi.GET_SCAN_CODE + "4clfc3wWvg==").execute(true).bodyStream();
        weChatController.login(inputStream);
        //此时应该发消息告诉核心库  可以开始监听是否扫描了二维码
        WeChatCore.getInstance().updateCheckScanState(true);

    }


    /*public static void main(String[] args) {


    }*/
}
