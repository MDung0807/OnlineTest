package com.iotstar.onlinetest.services.answer;

import com.iotstar.onlinetest.DTOs.AnswerDTO;
import com.iotstar.onlinetest.models.Answer;
import com.iotstar.onlinetest.models.Question;
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

    private Answer answer;
    @Override
    public void createAnswers(List<AnswerDTO> answerDTOs, Question question) {
        List<Answer> answers = new ArrayList<>();

        for (AnswerDTO i: answerDTOs){
            answer = mapper.map(i, Answer.class);
            answer.setQuestion(question);
            answers.add(answer);
        }
        Iterable<Answer> answers1 = new ArrayList<>();
        answers1=answers;
            answers = answerDAO.saveAll(answers1);
    }
}
