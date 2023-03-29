package com.iotstar.onlinetest.services.subject;


import com.iotstar.onlinetest.DTOs.requests.SubjectRequest;
import com.iotstar.onlinetest.DTOs.responses.SubjectResponse;
import com.iotstar.onlinetest.models.Subject;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.repositories.UserDAO;
import com.iotstar.onlinetest.repositories.subject.SubjectDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImp implements SubjectService{

    @Autowired
    private SubjectDAO subjectDAO;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserDAO userDAO;

    private Subject subject;

    @Override
    public SubjectResponse getSubject(Long subjectId) {
        subject = subjectDAO.findById(subjectId).get();
        return mapper.map(subject, SubjectResponse.class);
    }

    @Override
    public boolean existByUserId(Long subjectId, Long userId){
        subject = subjectDAO.findById(subjectId).get();
        for (User user: subject.getUsers()){
            if(userId == user.getUserId()){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<SubjectResponse> getAllSubject() {
        return null;
    }

    @Override
    public void createSubject(SubjectRequest subjectRequest, Long userId) {
        subject = mapper.map(subjectRequest, Subject.class);
        subject.setStatus(1);
        subject = subjectDAO.save(subject);
        User user = userDAO.getUserByUserId(userId);
        user.setSubject(subject);
    }

    @Override
    public void updateSubject(SubjectRequest subjectDTO) {

    }

    @Override
    public void delSubject(int id) {

    }
}
