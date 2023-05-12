package com.iotstar.onlinetest.statval;

public enum ESubject {

    SUBJECT_EXIST("Subject already exists"),
    SUBJECT_NOTFOUND("Not found information subject id: "),
    IMG_NAME_SUBJECT("subject_image_"),
    SUBJECT_DEPRECATED("Subject is deprecated");

    private final String des;
    ESubject(String des){
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
