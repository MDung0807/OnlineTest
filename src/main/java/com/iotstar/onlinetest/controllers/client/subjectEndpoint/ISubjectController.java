package com.iotstar.onlinetest.controllers.client.subjectEndpoint;

import com.iotstar.onlinetest.DTOs.requests.SubjectRequest;
import com.iotstar.onlinetest.DTOs.responses.Response;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/subject")
public interface ISubjectController {
    @RequestMapping("/add")
    @PreAuthorize("hasRole(@environment.getProperty('ROLE_TEACHER'))")
    ResponseEntity<?> addSubject(@Valid @ModelAttribute SubjectRequest subjectRequest);

    @GetMapping("/id")
    ResponseEntity<Response> getSubject (@RequestParam Long subjectId);

    @GetMapping({"/", ""})
    ResponseEntity<Response> getAllSubject(@RequestParam(required = false, defaultValue = "0") int index,
                                           @RequestParam(required = false, defaultValue = "10")int size);

    //deprecated
    @GetMapping("/del")
    ResponseEntity<Response> delSubject(@RequestParam Long subjectId);

    @RequestMapping("/updateImage")
    @PreAuthorize("hasRole(@environment.getProperty('ROLE_TEACHER'))")
    ResponseEntity<?> updateImage(@RequestParam Long subjectId, @ModelAttribute MultipartFile image);

    @GetMapping("/getSubjectByUserId")
    @PreAuthorize("hasRole(@environment.getProperty('ROLE_TEACHER'))")
    ResponseEntity<Response> getSubjectByUserId(@RequestParam Long userId);
}
