package com.iotstar.onlinetest.statval;

public enum EWish {
    WISHLIST_NOT_FOUND("not found wish list for user");
    private final String description;
    EWish(String des){

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
