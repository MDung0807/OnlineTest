package com.iotstar.onlinetest.services.subject.subjectMatter;

import com.iotstar.onlinetest.DTOs.SubjectMatterDTO;

public interface SubjectMatterService {
    public void create(SubjectMatterDTO subjectMatterDTO);

    public void del(int id);

    public void update(SubjectMatterDTO subjectMatterDTO);

    public SubjectMatterDTO getAllBySubject(int subjectId);
}
