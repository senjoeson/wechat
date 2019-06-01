package com.senjoeson.wechat.base;


import com.senjoeson.wechat.type.ResourceType;
import com.senjoeson.wechat.utils.TextUtils;

import java.net.URL;

import javax.management.modelmbean.XMLParseException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author MyPC
 * @date 2018/11/29
 */

public abstract class BaseApplication<T> extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        String fxmlResourceName = setFxmlResourceName();

        if (TextUtils.isEmpty(fxmlResourceName)) {
            throw new NullPointerException("引入的fxml资源文件不能为空");
        } else if (!fxmlResourceName.endsWith(".fxml")) {
            throw new XMLParseException("缺少必要的后缀名'.fxml'");
        }
        URL resource = getResourceURL(ResourceType.FXML, fxmlResourceName);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(resource);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent, primaryStage.getWidth(), primaryStage.getHeight());
        primaryStage.setScene(scene);
        initPage(fxmlLoader, primaryStage, parent);

        T newInstance = fxmlLoader.getController();
        if (newInstance instanceof BaseController) {

            ((BaseController) newInstance).init(primaryStage, parent);
            ((BaseController) newInstance).initData();
            ((BaseController) newInstance).initListener();
        } else {
            throw new Exception("必须指定Application的泛型，并泛型需实现BaseController");
        }
        //  initController(primaryStage, MainController.class);
        if (!primaryStage.isShowing()) {
            primaryStage.show();
        }

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
              //  System.exit(0);
                System.out.println("监听");
            }
        });
    }


    /**
     * 获取fxml资源文件
     *
     * @param resourceType     资源文件类型
     * @param fxmlResourceName 资源文件名称
     * @return
     */
    public static URL getResourceURL(Enum resourceType, String fxmlResourceName) {

        if (!TextUtils.isEmpty(fxmlResourceName)) {
            URL resource = BaseApplication.class.getClassLoader().getResource(resourceType + fxmlResourceName);
            System.out.println("resource = " + resource);
            return resource;
        } else {
            return null;
        }
    }

    /**
     * 获取图片路径
     *
     * @param imgResourceName 资源文件名称
     * @return
     */
    public static URL getImageURL(String imgResourceName) {
        return BaseApplication.class.getClassLoader().getResource(ResourceType.IMG + imgResourceName);
    }


    /**
     * 初始化controller
     *
     * @param lastStage      上一个页面
     * @param baseController 所有的controller必须继承basecontroller
     */
    public void initController(Stage lastStage, Parent parent, Class<? extends BaseController> baseController) {
        try {
            BaseController controller = baseController.newInstance();
            controller.init(lastStage, parent);
            controller.initListener();
            controller.initData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 只需要设置fxml名称即可
     */
    protected abstract String setFxmlResourceName();

    /***
     * 请设置一个scene 设置@primaryStage的长和宽
     *
     * @param fxmlLoader
     * @param primaryStage 原始平台
     * @param parent       父布局
     */
    protected abstract void initPage(FXMLLoader fxmlLoader, Stage primaryStage, Parent parent);


}
