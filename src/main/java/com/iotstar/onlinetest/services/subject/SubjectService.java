package com.iotstar.onlinetest.services.subject;

import com.iotstar.onlinetest.DTOs.requests.SubjectRequest;
import com.iotstar.onlinetest.DTOs.responses.SubjectResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SubjectService {

    boolean existByUserId(Long subjectId, Long userId);
    void createSubject(SubjectRequest subjectDTO, Long userId);

    void updateSubject(SubjectRequest subjectDTO);

    SubjectResponse getSubject(Long id);

    List<SubjectResponse> getAllSubject();

    void delSubject(Long id);
    void updateImage(Long id, MultipartFile image, Long userId);
    SubjectResponse getSubjectByUserId(Long userId);
}
