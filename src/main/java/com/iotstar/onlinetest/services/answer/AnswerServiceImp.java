package com.iotstar.onlinetest.services.answer;

import com.iotstar.onlinetest.DTOs.AnswerDTO;
import com.iotstar.onlinetest.models.Answer;
import com.iotstar.onlinetest.repositories.AnswerDAO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerServiceImp implements AnswerService{
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private AnswerDAO answerDAO;
    @Override
    public void createAnswers(List<AnswerDTO> answerDTOs) {
        List<Answer> answers = new ArrayList<>();
        for (AnswerDTO i: answerDTOs){
            answers.add(mapper.map(i, Answer.class));
        }
        Iterable<Answer> answers1 = new ArrayList<>();
        answers1=answers;
            answers = answerDAO.saveAll(answers1);
    }
}
