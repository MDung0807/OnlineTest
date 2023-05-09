package com.iotstar.onlinetest.statval;

public enum EReview {
    E_REVIEW_IN_TEST_NOTFOUND("Not found review in test"),
    E_REVIEW_NOTFOUND("Not found review");
    private final String description;
    EReview(String des){

        this.description = des;
    }

    public String getDescription() {
        return description;
    }
    public String getDescription(Long id) {
        return description+id;
    }
    public String getDescription(String message){
        return description+message;
    }
}
