package com.senjoeson.wechat.demo.demo3;

import com.alibaba.fastjson.JSONObject;
import com.senjoeson.wechat.Wechat;
import com.senjoeson.wechat.api.WechatTools;
import com.senjoeson.wechat.beans.BaseMsg;
import com.senjoeson.wechat.core.Core;
import com.senjoeson.wechat.face.IMsgHandlerFace;
import com.senjoeson.wechat.utils.MyHttpClient;
import com.senjoeson.wechat.utils.enums.StorageLoginInfoEnum;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 此示例演示如何获取所有好友的头像
 *
 * @author https://github.com/yaphone
 * @version 1.0
 * @date 创建时间：2017年6月26日 下午11:27:46
 */
public class PicYourFriends implements IMsgHandlerFace {
    private static final Core core = Core.getInstance();
    private static final MyHttpClient myHttpClient = core.getMyHttpClient();
    private static final String path = "D://itchat4j//head"; // 这里需要设置保存头像的路径
    private static Logger LOG = LoggerFactory.getLogger(PicYourFriends.class);

    public static void main(String[] args) {
        String qrPath = "D://itchat4j//login"; // 保存登陆二维码图片的路径，这里需要在本地新建目录
        IMsgHandlerFace msgHandler = new PicYourFriends(); // 实现IMsgHandlerFace接口的类
        Wechat wechat = new Wechat(msgHandler, qrPath); // 【注入】
        wechat.start(); // 启动服务，会在qrPath下生成一张二维码图片，扫描即可登陆，注意，二维码图片如果超过一定时间未扫描会过期，过期时会自动更新，所以你可能需要重新打开图片
    }

    @Override
    public String textMsgHandle(BaseMsg msg) {

        if (!msg.isGroupMsg()) { // 群消息不处理
            String text = msg.getText(); // 发送文本消息，也可调用MessageTools.sendFileMsgByUserId(userId,text);
            String baseUrl = "https://" + core.getIndexUrl(); // 基础URL
            String skey = (String) core.getLoginInfo().get(StorageLoginInfoEnum.skey.getKey());
            if (text.equals("111")) {
                LOG.info("开始下载好友头像");
                List<JSONObject> friends = WechatTools.getContactList();
                for (int i = 0; i < friends.size(); i++) {
                    JSONObject friend = friends.get(i);
                    String url = baseUrl + friend.getString("HeadImgUrl") + skey;
                    // String fileName = friend.getString("NickName");
                    String headPicPath = path + File.separator + i + ".jpg";

                    HttpEntity entity = myHttpClient.doGet(url, null, true, null);
                    try {
                        OutputStream out = new FileOutputStream(headPicPath);
                        byte[] bytes = EntityUtils.toByteArray(entity);
                        out.write(bytes);
                        out.flush();
                        out.close();

                    } catch (Exception e) {
                        LOG.info(e.getMessage());
                    }

                }
            }
        }
        return null;
    }

    @Override
    public String picMsgHandle(BaseMsg msg) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String voiceMsgHandle(BaseMsg msg) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String viedoMsgHandle(BaseMsg msg) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String nameCardMsgHandle(BaseMsg msg) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void sysMsgHandle(BaseMsg msg) {
        // TODO Auto-generated method stub

    }

    @Override
    public String verifyAddFriendMsgHandle(BaseMsg msg) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String mediaMsgHandle(BaseMsg msg) {
        // TODO Auto-generated method stub
        return null;
    }

}
