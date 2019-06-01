package com.senjoeson.wechat.config;

import java.io.File;

public class PathConfig {

    private static final String BASE_PATH = "D://itchat4j//";
    private static final String PATH_FILE = "D://itchat4j//file//";
    private static final String PATH_VIDEO = "D://itchat4j//video//";
    private static final String PATH_VOICE = "D://itchat4j//voice//";
    private static final String PATH_PIC = "D://itchat4j//pic//";


    private static String makeDirs(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }


    public static String getPathFile(String fileName) {
        return makeDirs(PATH_FILE).concat(fileName);

    }

    public static String getPathVideo(String fileName) {
        return makeDirs(PATH_VIDEO).concat(fileName).concat(".mp4");

    }

    public static String getPathVoice(String fileName) {
        return makeDirs(PATH_VOICE).concat(fileName).concat(".mp3");
    }

    public static String getPathPic(String fileName) {
        return makeDirs(PATH_PIC).concat(fileName).concat(".jpg");

    }

}
