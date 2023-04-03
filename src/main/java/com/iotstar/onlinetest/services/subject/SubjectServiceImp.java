package com.iotstar.onlinetest.services.subject;


import com.iotstar.onlinetest.DTOs.requests.SubjectRequest;
import com.iotstar.onlinetest.DTOs.responses.SubjectResponse;
import com.iotstar.onlinetest.exceptions.ResourceExistException;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.exceptions.UserNotFoundException;
import com.iotstar.onlinetest.models.Subject;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.repositories.UserDAO;
import com.iotstar.onlinetest.repositories.subject.SubjectDAO;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SubjectServiceImp implements SubjectService{

    @Autowired
    private SubjectDAO subjectDAO;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private FileUtils fileUtils;

    private Subject subject;

    @Override
    public SubjectResponse getSubject(Long subjectId) {
        subject = subjectDAO.findById(subjectId).orElseThrow(()-> new ResourceExistException(AppConstant.SUBJECT_NOTFOUND +subjectId));
        return mapper.map(subject, SubjectResponse.class);
    }

    @Override
    public boolean existByUserId(Long subjectId, Long userId){
        subject = subjectDAO.findById(subjectId).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.SUBJECT_NOTFOUND+subjectId));
        for (User user: subject.getUsers()){
            if(Objects.equals(userId, user.getUserId())){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<SubjectResponse> getAllSubject() {
        List<Subject> subjects= subjectDAO.findAll();
        List<SubjectResponse> subjectResponses = new ArrayList<>();
        for (Subject i: subjects){
            subjectResponses.add(mapper.map(i, SubjectResponse.class));
        }
        return subjectResponses;
    }

    @Override
    @Transactional
    public void createSubject(SubjectRequest subjectRequest, Long userId) {
        if (subjectDAO.existsByName(subjectRequest.getName())){
            throw new ResourceExistException(AppConstant.SUBJECT_EXIST);
        }
        subject = mapper.map(subjectRequest, Subject.class);
        subject.setStatus(1);
        subject = subjectDAO.save(subject);

        User user = userDAO.getUserByUserId(userId).orElseThrow(()->
                new UserNotFoundException(AppConstant.USER_NOTFOUND+userId));
        user.setSubject(subject);
        userDAO.save(user);
    }

    @Override
    public void updateSubject(SubjectRequest subjectRequest) {
        subject = subjectDAO.findById(subjectRequest.getSubjectId()).orElseThrow(()->
                new UserNotFoundException(AppConstant.SUBJECT_NOTFOUND+subjectRequest.getSubjectId()));
        if(subjectDAO.existsByName(subjectRequest.getName())){
            throw new ResourceExistException(AppConstant.SUBJECT_EXIST);
        }
        subject= mapper.map(subjectRequest, Subject.class);
        subject.setStatus(1);
        subject = subjectDAO.save(subject);
    }

    @Override
    public void delSubject(Long id) {
        subject = subjectDAO.findById(id).orElseThrow(()->
                new UserNotFoundException(AppConstant.SUBJECT_NOTFOUND+id));
    }
}
