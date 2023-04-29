package com.iotstar.onlinetest.utils;

import org.springframework.security.core.parameters.P;
import org.springframework.web.multipart.MultipartFile;

public class AppConstant {
    public static final String NOT_ENOUGH_QUESTION="Not enough question in database";
    public static final String USER_ERRORS = "User errors";
    public static final String NOT_FOUND = "Not Found";
    public static final String BAD_REQUEST = "Bad Request";
    public static final String SQL_EXCEPTION = "Sql query Error";
    public static final String INVALID_REQUEST = "Invalid Request";
    public static final String INTERNAL_VALID = "Internal error";
    public static  final String ACCESS_DENIED = "Access denied";

    //SUCCESS
    public static final String SUCCESS = "success";
    //Exception
    public static final String USERNAME_OR_PASSWORD_INCORRECT = "Username or password incorrect";
    public static final String UNAUTHORIZED_ERROR= "The login session has expired, please login again";

    // ACCOUNT
    public static final String ACCOUNT_LOCKED= "Account has been locked";
    public static final String RESET_PASSWORD_SUCCESS = "password reset success";
    public static final String INFO_ACC_NOTFOUND = "Not found information account: ";
    public static final String ACCOUNT_EXIST= "Account already exists";
    public static final String USERNAME_EXISTS = "Username already exists";

    //SUBJECT
    public static final String SUBJECT_EXIST="Subject already exists";
    public static final String SUBJECT_NOTFOUND = "Not found information subject id: ";
    public static final String IMG_NAME_SUBJECT = "subject_image_";

    //ROLE
    public static final String ROLE_EXIST = "Role already exists";
    public static final String ROLE_NOTFOUND= "Not found role: ";
    public static final String ROLE_USER = "user";
    public static final String ROLE_TEACHER="teacher";
    public static final String ROLE_ADMIN = "admin";

    //USER
    public static final String USER_REGISTER_SUCCESS = "User register success";
    public static final String USER_NOTFOUND="Not found userId: ";
    public static final String IMG_NAME_USER = "user_image";
    public static final String EMAIL_EXISTS = "Email already exists";
    public static final String PHONE_NUMBER_EXISTS = "Phone Number already exists";
    public static final String USER_HAVEN_SUBJECT = "User haven subject";
    //TOPIC
    public static final String TOPIC_NOTFOUND="Not found topicId: ";
    public static final String IMG_NAME_TOPIC="topic_img_";

    //QUESTION
    public static final String IMG_NAME_QUESTION="question_img_";
    public static final String QUESTION_NOTFOUND = "Not found question: ";

    //TEST
    public static final String TEST_NOTFOUND="Not found test: ";
}
