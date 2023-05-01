package com.iotstar.onlinetest.statval;

public enum EAccount {
    ACCOUNT_LOCKED("Account has been locked"),
    RESET_PASSWORD_SUCCESS("password reset success"),
    INFO_ACC_NOTFOUND ("Not found information account"),
    ACCOUNT_EXIST("Account already exists");
    private final String des;
    EAccount(String des){
        this.des = des;
    }

    public String getDes(String mess) {
        return des+mess;
    }
    public String getDes(Long id) {
        return des+id;
    }
    public String getDes() {
        return des;
    }
}
