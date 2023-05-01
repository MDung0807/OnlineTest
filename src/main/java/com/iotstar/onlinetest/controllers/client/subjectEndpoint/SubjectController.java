package com.iotstar.onlinetest.controllers.client.subjectEndpoint;

import com.iotstar.onlinetest.DTOs.requests.SubjectRequest;
import com.iotstar.onlinetest.DTOs.responses.MessageResponse;
import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.DTOs.responses.SubjectResponse;
import com.iotstar.onlinetest.common.paging.extend.Paging;
import com.iotstar.onlinetest.exceptions.ResourceExistException;
import com.iotstar.onlinetest.services.subject.SubjectService;
import com.iotstar.onlinetest.services.user.UserService;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.AuthUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class SubjectController implements ISubjectController{
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthUtils authUtils;
    @Autowired
    private Paging paging;

    @Override
    public ResponseEntity<?> addSubject(SubjectRequest subjectRequest){
        Long userId = authUtils.getAccountDetail().getUserId();
        //Check user created the subject
        if (userService.existsSubject(userId))
            throw new ResourceExistException(AppConstant.USER_HAVEN_SUBJECT);
        subjectService.createSubject(subjectRequest, userId);
        return ResponseEntity.ok(
                new Response(false,
                        new MessageResponse(AppConstant.SUCCESS))
        );
    }

    @Override
    public ResponseEntity<Response> getSubject (Long subjectId){
        SubjectResponse subjectResponse = subjectService.getSubject(subjectId);
        return ResponseEntity.ok(
                new Response(false, subjectResponse)
        );
    }

    @Override
    public ResponseEntity<Response> getAllSubject(int index, int size){
        paging.setPageSize(size);
        paging.setPageIndex(index);
        List<SubjectResponse> subjectResponses = subjectService.getAllSubject();
        return ResponseEntity.ok(
                new Response(false, subjectResponses)
        );
    }

    @Override
    public ResponseEntity<Response> delSubject(Long subjectId){
        Long userId = authUtils.getAccountDetail().getUserId();
        if (userService.existsSubjectById(userId, subjectId)){
            subjectService.delSubject(subjectId);
            return new ResponseEntity<>(
                    new Response(false, new MessageResponse(AppConstant.SUCCESS)),
                    HttpStatus.OK
            );
        }
        throw new AccessDeniedException(AppConstant.ACCESS_DENIED);

    }

    @Override
    public ResponseEntity<?> updateImage(Long subjectId, MultipartFile image){
        Long userId = authUtils.getAccountDetail().getUserId();
        //Check user created the subject
        subjectService.updateImage(subjectId, image, userId);
        return ResponseEntity.ok(
                new Response(false,
                        new MessageResponse(AppConstant.SUCCESS))
        );
    }
}
