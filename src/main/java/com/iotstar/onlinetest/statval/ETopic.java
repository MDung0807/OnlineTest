package com.iotstar.onlinetest.statval;

public enum ETopic {
    TOPIC_NOTFOUND("Not found topicId: "),
    IMG_NAME_TOPIC("topic_img_"),
    TOPIC_DEPRECATED ("Topic Deprecated");

    public final String des;
    ETopic(String des){
        this.des = des;
    }

    public String getDes() {
        return des;
    }
    public String getDes(Long id) {
        return des+id;
    }
    public String getDes(String mess) {
        return des+mess;
    }
}
