package com.senjoeson.wechat.type;

public enum ResourceType {

    FXML("FXML", "fxml/"), CSS("CSS", "css/"), IMG("IMG", "img/"),CONFIG("CONFIG","config/");


    private String enumKey;
    private String enumValue;


    // 构造方法
    ResourceType(String name, String index) {
        this.enumKey = name;
        this.enumValue = index;
    }

    public String getEnumKey() {
        return enumKey;
    }

    public ResourceType setEnumKey(String enumKey) {
        this.enumKey = enumKey;
        return this;
    }

    public String getEnumValue() {
        return enumValue;
    }

    public ResourceType setEnumValue(String enumValue) {
        this.enumValue = enumValue;
        return this;
    }


    /**
     * 根据key获取枚举
     * @param key
     * @return
     */
    public static ResourceType getEnumByKey(String key) {
        if (null == key) {
            return null;
        }
        for (ResourceType temp : ResourceType.values()) {
            if (temp.getEnumKey().equals(key)) {
                return temp;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.enumValue;
    }
}



