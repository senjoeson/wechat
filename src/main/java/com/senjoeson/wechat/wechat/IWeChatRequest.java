package com.senjoeson.wechat.wechat;

import javafx.beans.Observable;
import retrofit2.http.GET;

import java.util.Map;

public interface IWeChatRequest {


    public void getUUID(String requestUrl, Map<String, String> hashMap);


    void getUUID();






}
