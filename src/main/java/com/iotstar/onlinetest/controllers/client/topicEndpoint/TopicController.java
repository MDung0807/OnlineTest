package com.iotstar.onlinetest.controllers.client.topicEndpoint;

import com.iotstar.onlinetest.DTOs.requests.TopicRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.DTOs.responses.TopicResponse;
import com.iotstar.onlinetest.services.topic.TopicPaging;
import com.iotstar.onlinetest.services.topic.TopicService;
import com.iotstar.onlinetest.services.user.UserService;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class TopicController implements ITopicEndpoint{
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;
    @Autowired
    private TopicPaging paging;
    @Autowired
    private AuthUtils authUtils;

    @Override
    public ResponseEntity<?> addTopic (TopicRequest param1, MultipartFile image, TopicRequest param2){
        Long userId = authUtils.getAccountDetail().getUserId();
        TopicRequest topicRequest;
        if(param2 == null)
            topicRequest = param1;
        else
            topicRequest = param2;
        topicRequest.setImage(image);

        if (userService.existsSubjectById(userId, topicRequest.getSubjectId())){
            topicService.create(topicRequest);
            return ResponseEntity.ok(
                    new Response(false,
                            new MessageResponse(AppConstant.SUCCESS))
            );
        }

        throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
    }

    @Override
    public ResponseEntity<?> getTopicBySubjectId(Long subjectId, int index, int size){
        paging.setPageIndex(index);
        paging.setPageSize(size);
        List<TopicResponse> topicResponses = topicService.getAllBySubject(subjectId);
        return ResponseEntity.ok(
                new Response(false, topicResponses)
        );
    }

    @Override
    public ResponseEntity<Response> delTopicById(Long topicId){
        Long userId = authUtils.getAccountDetail().getUserId();
        topicService.del(topicId, userId);
        return new ResponseEntity<>(
                new Response(false, new MessageResponse(AppConstant.SUCCESS))
                , HttpStatus.OK);
    }
}
