package com.senjoeson.wechat.utils.tools;

import com.senjoeson.wechat.beans.BaseMsg;
import com.senjoeson.wechat.core.Core;

import org.apache.http.HttpEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.senjoeson.wechat.beans.BaseMsg;
import com.senjoeson.wechat.core.Core;
import com.senjoeson.wechat.utils.MyHttpClient;
import com.senjoeson.wechat.utils.enums.MsgTypeEnum;
import com.senjoeson.wechat.utils.enums.URLEnum;

/**
 * 下载工具类
 *
 * @author https://github.com/yaphone
 * @version 1.0
 * @date 创建时间：2017年4月21日 下午11:18:46
 */
public class DownloadTools {
    private static Logger logger = Logger.getLogger("DownloadTools");
    private static Core core = Core.getInstance();
    private static MyHttpClient myHttpClient = core.getMyHttpClient();

    /**
     * 处理下载任务
     *
     * @param path
     * @return
     * @author https://github.com/yaphone
     * @date 2017年4月21日 下午11:00:25
     */
    public static Object getDownloadFn(BaseMsg msg, String type, String path) {
        Map<String, String> headerMap = new HashMap<String, String>();
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        String url = "";
        if (type.equals(MsgTypeEnum.PIC.getType())) {
            url = String.format(URLEnum.WEB_WX_GET_MSG_IMG.getUrl(), (String) core.getLoginInfo().get("url"));
        } else if (type.equals(MsgTypeEnum.VOICE.getType())) {
            url = String.format(URLEnum.WEB_WX_GET_VOICE.getUrl(), (String) core.getLoginInfo().get("url"));
        } else if (type.equals(MsgTypeEnum.VIEDO.getType())) {
            headerMap.put("Range", "bytes=0-");
            url = String.format(URLEnum.WEB_WX_GET_VIEDO.getUrl(), (String) core.getLoginInfo().get("url"));
        } else if (type.equals(MsgTypeEnum.MEDIA.getType())) {
            headerMap.put("Range", "bytes=0-");
            url = String.format(URLEnum.WEB_WX_GET_MEDIA.getUrl(), (String) core.getLoginInfo().get("fileUrl"));
            params.add(new BasicNameValuePair("sender", msg.getFromUserName()));
            params.add(new BasicNameValuePair("mediaid", msg.getMediaId()));
            params.add(new BasicNameValuePair("filename", msg.getFileName()));
        }
        params.add(new BasicNameValuePair("msgid", msg.getNewMsgId()));
        params.add(new BasicNameValuePair("skey", (String) core.getLoginInfo().get("skey")));
        HttpEntity entity = myHttpClient.doGet(url, params, true, headerMap);
        try {
            OutputStream out = new FileOutputStream(path);
            byte[] bytes = EntityUtils.toByteArray(entity);
            out.write(bytes);
            out.flush();
            out.close();
            // Tools.printQr(path);

        } catch (Exception e) {
            logger.info(e.getMessage());
            return false;
        }
        return null;
    }


}
