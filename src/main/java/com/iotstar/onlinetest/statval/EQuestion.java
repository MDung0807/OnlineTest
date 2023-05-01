package com.iotstar.onlinetest.statval;

public enum EQuestion {
    IMG_NAME_QUESTION("question_img_"),
    QUESTION_NOTFOUND("Not found question: \"");

    private final String des;
    EQuestion(String des){
        this.des = des;
    }

    public String getDes() {
        return des;
    }
    public String getDes(Long id) {
        return des+id;
    }
}
