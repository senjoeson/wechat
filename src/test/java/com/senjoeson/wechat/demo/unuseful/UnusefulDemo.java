package com.senjoeson.wechat.demo.unuseful;

import com.senjoeson.wechat.Wechat;
import com.senjoeson.wechat.api.AssistTools;
import com.senjoeson.wechat.beans.BaseMsg;
import com.senjoeson.wechat.face.IMsgHandlerFace;

import java.io.IOException;

/**
 * 自用的测试类，请无视
 *
 * @author https://github.com/yaphone
 * @version 1.0
 * @date 创建时间：2017年5月22日 下午10:41:44
 */
public class UnusefulDemo implements IMsgHandlerFace {

    public static void main(String[] args) {
        IMsgHandlerFace msgHandler = new UnusefulDemo();
        Wechat wechat = new Wechat(msgHandler, "D://itchat4j/login");
        wechat.start();
    }

    @Override
    public String textMsgHandle(BaseMsg msg) {
        if (!msg.isGroupMsg()) { // 群消息不处理
            String text = msg.getText(); // 发送文本消息，也可调用MessageTools.sendFileMsgByUserId(userId,text);
            if (text.equals("111")) {
                String username = "yaphone";
                String password = "123456";
                String localPath = "D://itchat4j/pic/1.jpg";
                String uploadUrl = "http://127.0.0.1/file/put";
                try {
                    AssistTools.sendQrPicToServer(username, password, uploadUrl, localPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return text;
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
