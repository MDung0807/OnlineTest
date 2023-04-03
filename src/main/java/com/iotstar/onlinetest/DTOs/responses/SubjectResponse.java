package com.iotstar.onlinetest.DTOs.responses;

import com.iotstar.onlinetest.models.Topic;
import com.iotstar.onlinetest.models.User;
import lombok.Data;

import java.util.List;

@Data
public class SubjectResponse {
    private Long subjectId;
    private String name;
    private int status;
    private String image;
    private List<User> users;
    private List<Topic> topics;
}
