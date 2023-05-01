package com.iotstar.onlinetest.statval;

public enum ERole {
    ROLE_EXIT("Role already exists"),
    ROLE_NOTFOUND("Not found role");

    private final String des;
    ERole(String des){
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public String getDes(String mess) {
        return des+mess;
    }
}
