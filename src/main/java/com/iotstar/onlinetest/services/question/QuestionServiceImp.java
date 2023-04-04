package com.iotstar.onlinetest.services.question;

import com.iotstar.onlinetest.DTOs.AnswerDTO;
import com.iotstar.onlinetest.DTOs.requests.QuestionRequest;
import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.models.Question;
import com.iotstar.onlinetest.models.User;
import com.iotstar.onlinetest.repositories.AnswerDAO;
import com.iotstar.onlinetest.repositories.QuestionDAO;
import com.iotstar.onlinetest.repositories.UserDAO;
import com.iotstar.onlinetest.services.answer.AnswerService;
import com.iotstar.onlinetest.utils.AppConstant;
import com.iotstar.onlinetest.utils.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class QuestionServiceImp implements QuestionService{
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private QuestionDAO questionDAO;
    @Autowired
    private FileUtils fileUtils;
    @Autowired
    private AnswerService answerService;

    private Question question;
    @Autowired
    private UserDAO userDAO;


    public Question findById(Long id){
        question = questionDAO.findById(id).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.QUESTION_NOTFOUND+id));
        return question;
    }

    private Question uploadImage(MultipartFile fileInput, Long id){
        question = findById(id);
        question.setImage(fileUtils.upload(fileInput, AppConstant.IMG_NAME_QUESTION+id));
        question =questionDAO.save(question);
        return question;
    }

    @Override
    public Question createQuestion(QuestionRequest questionRequest, Long userId) {
        mapper.typeMap(QuestionRequest.class, Question.class).addMappings(mapper->mapper.skip(Question::setImage));

        //Get User
        User user = userDAO.getUserByUserId(userId).orElseThrow(()->
                new ResourceNotFoundException(AppConstant.USER_NOTFOUND+userId));
        question = mapper.map(questionRequest, Question.class);
        question.setStatus(1);

        question.setUser(user);
        question = questionDAO.save(question);
        question = uploadImage(questionRequest.getImage(), questionRequest.getQuestionId());

        answerService.createAnswers(questionRequest.getAnswerDTOs());
        return question;
    }
}
