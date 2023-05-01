package com.iotstar.onlinetest.statval;

public enum ETest {
    NOT_ENOUGH_QUESTION("Not enough question in database"),
    TEST_NOTFOUND("Not found test: ");

    private final String des;
    ETest(String des){
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public String getDes(Long id) {
        return des+id;
    }
}
