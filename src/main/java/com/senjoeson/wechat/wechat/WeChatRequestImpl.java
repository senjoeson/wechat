package com.senjoeson.wechat.wechat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class WeChatRequestImpl implements IWeChatRequest {


    //public static final String APPID = "wxeb7ec651dd0aefa9";
    public static final String APP_ID = "wx782c26e4c19acffb";


    private static final Logger log = LoggerFactory.getLogger(WeChatRequestImpl.class);


    @Override
    public void getUUID(String requestUrl, Map<String, String> hashMap) {


    }

    @Override
    public void getUUID() {
        /*Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("appid", APPID);
        hashMap.put("fun", "new");
        hashMap.put("lang", "zh_CN");
        hashMap.put("_", String.valueOf(System.currentTimeMillis()));
        String post = HttpUtil.post(WeApi.GET_UUID, hashMap);
        System.out.println();*/
    }

    public static void main(String[] args) {
        /*Map<String, Object> hashMap = new HashMap<>();
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
            System.exit(0);
        }
*/




    }
}
