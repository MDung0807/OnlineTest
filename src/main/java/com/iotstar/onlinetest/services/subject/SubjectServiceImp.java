package com.iotstar.onlinetest.services.subject;


import com.iotstar.onlinetest.DTOs.requests.SubjectRequest;
import com.iotstar.onlinetest.DTOs.responses.SubjectResponse;
import com.iotstar.onlinetest.exceptions.ResourceExistException;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.models.Subject;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.repositories.SubjectDAO;
import com.iotstar.onlinetest.services.user.UserService;
import com.iotstar.onlinetest.statval.ESubject;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SubjectServiceImp extends SubjectPaging implements SubjectService{

    @Autowired
    private SubjectDAO subjectDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private FileUtils fileUtils;

    private Subject subject;

    public Subject getSubjectReturnSubject(Long id){
        return subjectDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException(ESubject.SUBJECT_NOTFOUND.getDes(id)));
    }
    @Override
    public SubjectResponse getSubject(Long subjectId) {
        subject = getSubjectReturnSubject(subjectId);
        return mapper.map(subject, SubjectResponse.class);
    }


    //deprecated
    @Override
    public boolean existByUserId(Long subjectId, Long userId){
        subject = getSubjectReturnSubject(subjectId);
        for (User user: subject.getUsers()){
            if(Objects.equals(userId, user.getUserId())){
                return true;
            }
        }
        return false;


    }

    @Override
    public List<SubjectResponse> getAllSubject() {
        List<Subject> subjects= subjectDAO.findAll(pageable()).getContent();
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
            userService.addSubjectInUser(userId, subjectDAO.findByName(subjectRequest.getName()));
            return;
        }

        mapper.typeMap(SubjectRequest.class, Subject.class).addMappings(mapper -> mapper.skip(Subject::setImage));
        subject = mapper.map(subjectRequest, Subject.class);
        subject.setStatus(1);
        subject = subjectDAO.save(subject);
        subject.setImage(
                fileUtils.upload(subjectRequest.getImage(),
                        ESubject.IMG_NAME_SUBJECT.getDes(subject.getSubjectId())));
        subject = subjectDAO.save(subject);
        userService.addSubjectInUser(userId, subject);
    }

    @Override
    @Transactional
    public void updateSubject(SubjectRequest subjectRequest) {
        subject = getSubjectReturnSubject(subjectRequest.getSubjectId());
        if(subjectDAO.existsByName(subjectRequest.getName())){
            throw new ResourceExistException(ESubject.SUBJECT_EXIST.getDes());
        }
        subject= mapper.map(subjectRequest, Subject.class);
        subject.setStatus(1);
        subject = subjectDAO.save(subject);
    }

    @Override
    @Transactional
    public void delSubject(Long id) {
        subject = getSubjectReturnSubject(id);
        subject.setStatus(0);
        subjectDAO.save(subject);
    }

    @Override
    public void updateImage(Long id, MultipartFile image, Long userId) {
        if (!userService.existsSubjectById(userId, id)){
            throw new AccessDeniedException(AppConstant.ACCESS_DENIED);
        }
        subject = getSubjectReturnSubject(id);
        subject.setImage(
                fileUtils.upload(image,
                        ESubject.IMG_NAME_SUBJECT.getDes(subject.getSubjectId())));
        subject = subjectDAO.save(subject);
    }
}
