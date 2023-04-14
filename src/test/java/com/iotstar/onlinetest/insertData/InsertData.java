package com.iotstar.onlinetest.insertData;



import com.iotstar.onlinetest.controllers.admin.RoleController;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.models.*;
import com.iotstar.onlinetest.repositories.AccountDAO;
import com.iotstar.onlinetest.repositories.RoleDAO;
import com.iotstar.onlinetest.repositories.UserDAO;
import com.iotstar.onlinetest.repositories.subject.SubjectDAO;
import com.iotstar.onlinetest.repositories.subject.TopicDAO;
import com.iotstar.onlinetest.utils.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
@Rollback(false)
public class InsertData {
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private AccountDAO accountDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private SubjectDAO subjectDAO;
    @Autowired
    private TopicDAO topicDAO;

    private Role role;
    private Account account;
    private User user;
    private Subject subject;
    private Topic topic;


    private void testRole(){
        role = Role.builder()
                .roleName("admin")
                .status(1).build();
        roleDAO.save(role);

        role = Role.builder()
                .roleName("student")
                .status(1).build();
        roleDAO.save(role);

        role = Role.builder()
                .roleName("teacher")
                .status(1).build();
        roleDAO.save(role);
    }

    private void testUser(){

        subject = subjectDAO.getReferenceById(1L);
        user = User.builder()
                .email("dominhdung@gmail.com")
                .phoneNumber("0121458759")
                .status(1)
                .subject(subject)
                .dateCreate(LocalDateTime.now())
                .firstName("Đỗ Minh")
                .lastName("Dũng")
                .build();
        userDAO.save(user);

        subject = subjectDAO.getReferenceById(2L);
        user = User.builder()
                .email("tan@gmail.com")
                .phoneNumber("01254789568")
                .status(1)
                .dateCreate(LocalDateTime.now())
                .firstName("Nguyễn Thái Ngọc")
                .lastName("Tân")
                .subject(subject)
                .build();
        userDAO.save(user);

        user = User.builder()
                .email("huyen@gmail.com")
                .phoneNumber("0125478541")
                .status(1)
                .dateCreate(LocalDateTime.now())
                .firstName("Nguyễn TTH")
                .lastName("Huyền")
                .build();
        userDAO.save(user);

        user = User.builder()
                .email("Ka@gmail.com")
                .phoneNumber("0321456897")
                .status(1)
                .dateCreate(LocalDateTime.now())
                .firstName("Phan Van")
                .lastName("Ka")
                .build();
        userDAO.save(user);
    }
    private void testAccount(){
        Role roleTeacher = roleDAO.getByRoleName("teacher").orElseThrow(()->
                new ResourceNotFoundException(AppConstant.ROLE_NOTFOUND));
        Role roleUser = roleDAO.getByRoleName("student").orElseThrow(()->
                new ResourceNotFoundException(AppConstant.ROLE_NOTFOUND));

        User user1 = userDAO.findById(1L).get();
        account = Account.builder()
                .username("minhdung123")
                .password("$2a$10$QdV044BmOtOWjDCDn.BIkOEa5R2SCAZgshW8fikwsE2f0sPPq33Vq")
                .status(1)
                .user(user1)
                .role(roleTeacher)
                .build();
        accountDAO.save(account);

        User user2 = userDAO.findById(2L).get();
        account = Account.builder()
                .username("tan123")
                .password("$2a$10$QdV044BmOtOWjDCDn.BIkOEa5R2SCAZgshW8fikwsE2f0sPPq33Vq")
                .status(1)
                .user(user2)
                .role(roleTeacher)
                .build();
        accountDAO.save(account);

        User user3 = userDAO.findById(3L).get();
        account = Account.builder()
                .username("huyen123")
                .password("$2a$10$QdV044BmOtOWjDCDn.BIkOEa5R2SCAZgshW8fikwsE2f0sPPq33Vq")
                .status(1)
                .user(user3)
                .role(roleUser)
                .build();
        accountDAO.save(account);
    }

    private void testSubject (){
        subject = Subject.builder()
                .name("toán")
                .status(1).build();
        subjectDAO.save(subject);

        subject = Subject.builder()
                .name("lý")
                .status(1).build();
        subjectDAO.save(subject);

        subject = Subject.builder()
                .name("hóa")
                .status(1).build();
        subjectDAO.save(subject);
    }

    private void testTopic(){

        Subject subjectMath = subjectDAO.getReferenceById(1L);
        Subject subjectPhys = subjectDAO.getReferenceById(2L);
        topic = Topic.builder()
                .name("Vi phân")
                .subject(subjectMath).build();
        topicDAO.save(topic);

        topic = Topic.builder()
                .name("Đạo hàm")
                .subject(subjectMath).build();
        topicDAO.save(topic);

        topic = Topic.builder()
                .name("hàm số")
                .subject(subjectMath).build();
        topicDAO.save(topic);

        topic = Topic.builder()
                .name("Chuyển động thẳng đều")
                .subject(subjectPhys).build();
        topicDAO.save(topic);

        topic = Topic.builder()
                .name("Chuyển động nhanh dần đều")
                .subject(subjectPhys).build();
        topicDAO.save(topic);

        topic = Topic.builder()
                .name("Sóng ánh sáng")
                .subject(subjectPhys).build();
        topicDAO.save(topic);
    }

    @Test
    void name() {
        testRole();
        testSubject();
        testUser();
        testTopic();
        testAccount();

    }
}
