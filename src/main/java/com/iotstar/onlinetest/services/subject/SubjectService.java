package com.iotstar.onlinetest.services.subject;

import com.iotstar.onlinetest.DTOs.SubjectDTO;

import java.util.List;

public interface SubjectService {
    public void createSubject(SubjectDTO subjectDTO);

    public void updateSubject(SubjectDTO subjectDTO);

    public void delSubject(int id);

    public SubjectDTO getSubject();

    public List<SubjectDTO> getAllSubject();
}
