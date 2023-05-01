package com.iotstar.onlinetest.statval;

public enum EHistory {

    NOT_FOUND("Not found test history");
    private final String des;
    EHistory(String des){
        this.des = des;
    }

    public String getDes() {
        return des;
    }
}
