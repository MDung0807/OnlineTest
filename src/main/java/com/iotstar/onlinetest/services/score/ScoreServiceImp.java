package com.iotstar.onlinetest.services.score;

import com.iotstar.onlinetest.DTOs.responses.ScoreResponse;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.exceptions.UserNotFoundException;
import com.iotstar.onlinetest.models.Score;
import com.iotstar.onlinetest.models.Test;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.models.UserTest;
import com.iotstar.onlinetest.repositories.ScoreDAO;
import com.iotstar.onlinetest.repositories.TestDAO;
import com.iotstar.onlinetest.repositories.UserDAO;
import com.iotstar.onlinetest.services.test.TestService;
import com.iotstar.onlinetest.services.test.TestServiceImp;
import com.iotstar.onlinetest.services.user.UserService;
import com.iotstar.onlinetest.services.user.UserServiceImp;
import com.iotstar.onlinetest.utils.AppConstant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.time.LocalDateTime;

@Service
public class ScoreServiceImp implements ScoreService{
    @Autowired
    private ScoreDAO scoreDAO;
    @Autowired
    private UserServiceImp userServiceImp;
    @Autowired
    private TestServiceImp testServiceImp;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TestDAO testDAO;

    private Score score;
    @Override
    @Transactional
    public void setScore(Long userId, Long testId, String scoreText) {
        User user = userServiceImp.getUserReturnUser(userId);
        Test test = testServiceImp.getTestReturnTest(testId);
        score = new Score();
        score.setUser(user);
        score.setTest(test);
        score.setScores(scoreText);
        score.setDateTest(LocalDateTime.now());
        score = scoreDAO.save(score);
    }

    @Override
    public ScoreResponse getScore(Long userId, Long testId) {
        User user = userDAO.getUserByUserId(userId).orElseThrow(()-> new UserNotFoundException(AppConstant.USER_NOTFOUND+userId));
        Test test = testDAO.findById(testId).orElseThrow(()-> new ResourceNotFoundException("not found"));
        score = scoreDAO.findByTestAndUser(test, user).orElseThrow(()-> new ResourceNotFoundException("notFound"));
        return mapper.map(score, ScoreResponse.class);
    }
}