package com.iotstar.onlinetest.services.subject;

import com.iotstar.onlinetest.DTOs.requests.SubjectRequest;
import com.iotstar.onlinetest.DTOs.responses.SubjectResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SubjectService {

    public boolean existByUserId(Long subjectId, Long userId);
    public void createSubject(SubjectRequest subjectDTO, Long userId);

    public void updateSubject(SubjectRequest subjectDTO);

    public SubjectResponse getSubject(Long id);

    public List<SubjectResponse> getAllSubject();

    public void delSubject(Long id);
    public void updateImage(Long id, MultipartFile image, Long userId);
}
