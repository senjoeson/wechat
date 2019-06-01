package com.senjoeson.wechat;

import com.senjoeson.wechat.base.BaseApplication;
import com.senjoeson.wechat.controller.WeChatController;
import com.senjoeson.wechat.core.Core;
import com.senjoeson.wechat.task.CheckLoginInitTask;
import com.senjoeson.wechat.task.CheckLoginTask;
import com.senjoeson.wechat.utils.tools.CommonTools;
import com.senjoeson.wechat.wechat.WeApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.regex.Matcher;

import cn.hutool.http.HttpUtil;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import static com.senjoeson.wechat.wechat.WeChatRequestImpl.APP_ID;
@SpringBootApplication
public class WeChatApplication extends BaseApplication<WeChatController> {

    public CheckLoginTask mCheckLoginTask;

    @Override
    protected String setFxmlResourceName() {
        return "wechat_root.fxml";
    }

    // long downloadFile = HttpUtil.downloadFile(WeApi.GET_SCAN_CODE + "4clfc3wWvg==", "C:\\Users\\kayakAndroid\\Desktop\\wechat\\login\\login.png");
    private static Logger log = LoggerFactory.getLogger(WeChatApplication.class);

    public static Timer timer = new Timer();

    @Override
    protected void initPage(FXMLLoader fxmlLoader, Stage primaryStage, Parent parent) {

        WeChatController weChatController = fxmlLoader.getController();
        String uuid = getUUID();
        if (!uuid.isEmpty()) {
            Core.getInstance().setUuid(uuid);
        }
        InputStream inputStream = HttpUtil.createGet(WeApi.GET_SCAN_CODE + uuid).execute(true).bodyStream();
        weChatController.login(inputStream);
        //此时应该发消息告诉核心库  可以开始监听是否扫描了二维码

        mCheckLoginTask = new CheckLoginTask();
        timer.schedule(mCheckLoginTask, 1000, 1);
        timer.schedule(new CheckLoginInitTask(),2000,1);

    }


    public String getUUID() {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("appid", APP_ID);
        hashMap.put("fun", "new");
        hashMap.put("lang", "zh_CN");
        hashMap.put("_", String.valueOf(System.currentTimeMillis()));
        String result = HttpUtil.post(WeApi.GET_UUID, hashMap);
        System.out.println(result);
        String regEx = "window.QRLogin.code = (\\d+); window.QRLogin.uuid = \"(\\S+?)\";";
        Matcher matcher = CommonTools.getMatcher(regEx, result);
        String uuid = "";
        if (matcher.find()) {
            String responseCode = matcher.group(1);
            uuid = matcher.group(2);
            System.out.println(responseCode);
            System.out.println(uuid);
        } else {
            log.info("获取UUID时,数据格式发生错误");
            //  System.exit(0);
        }

        return uuid;
    }

    @Override
    public void stop() throws Exception {
        System.out.println("执行了stop方法吗?");
        super.stop();
    }


    public static void main(String[] args) {
        SpringApplication.run(WeChatApplication.class, args);
        launch(args);
    }
}
