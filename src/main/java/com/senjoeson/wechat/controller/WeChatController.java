package com.senjoeson.wechat.controller;

import com.senjoeson.wechat.base.BaseController;
import com.senjoeson.wechat.wechat.WeChatCore;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.InputStream;

public class WeChatController implements BaseController {


    @FXML
    ImageView loginImage;


    @Override
    public void init(Stage stage, Parent parent) {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        /*Image image = new Image();
        loginImage.getImage()*/
    }

    public void login(String loginUrl) {
        Image image = new Image(loginUrl);
        loginImage.setImage(image);
    }

    public void login(InputStream inputStream) {
        Image image = new Image(inputStream);
        loginImage.setImage(image);
    }

    public void changeState(MouseEvent mouseEvent) {
        WeChatCore.getInstance().updateCheckScanState(!WeChatCore.getInstance().getCheckScanState());

    }
}
