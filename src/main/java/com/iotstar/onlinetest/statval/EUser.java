package com.iotstar.onlinetest.statval;

public enum EUser {
    USER_NOT_FOUND("Not found userId: "),
    USER_ERRORS("User errors"),
    USER_REGISTER_SUCCESS("User register success"),
    UNAUTHORIZED_ERROR("UNAUTHORIZED_ERROR"),
    IMG_NAME_USER("image_user_"),
    EMAIL_EXISTS("Email already exists"),
    PHONE_NUMBER_EXISTS ("Phone Number already exists"),
    USER_HAVEN_SUBJECT("User haven subject"),
    PASSWORD_NOT_CORRECT("Password confirm is not correct");
    private final String description;
    EUser(String des){

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
