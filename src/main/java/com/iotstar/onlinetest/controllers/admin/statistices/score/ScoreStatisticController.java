package com.iotstar.onlinetest.controllers.admin.statistices.score;

import com.iotstar.onlinetest.DTOs.responses.Response;
import com.iotstar.onlinetest.common.paging.PagingRequest;
import com.iotstar.onlinetest.models.statistics.ScoreStatistic;
import com.iotstar.onlinetest.services.statistices.socre.ScoreStatisticPaging;
import com.iotstar.onlinetest.services.statistices.socre.ScoreStatisticService;
import com.iotstar.onlinetest.services.statistices.socre.ScoreStatisticServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin
public class ScoreStatisticController implements IScoreStatisticController {

    @Autowired
    private ScoreStatisticService service;
    @Autowired
    private ScoreStatisticPaging paging;
    @Qualifier("scoreStatisticServiceImp")
    @Autowired
    private PagingRequest pagingRequest;

    @Override
    public ResponseEntity<Response> getScoreStatisticInSubject(int index, int size,
                                                               String sort, Long subjectId) {
        List<ScoreStatistic> responses = service.getScoreStatisticInSubject(subjectId);
        return new ResponseEntity<>(
                new Response(false, responses),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Response> getScoreStatisticInTopic(int index, int size,
                                                             String sort, Long topicId) {
        List<ScoreStatistic> responses = service.getScoreStatisticInTopic(topicId);
        return new ResponseEntity<>(
                new Response(false, responses),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Response> getScoreStatisticInTest(int index, int size,
                                                            String param, Long testId) {
//        paging.setPageSize(size);
//        paging.setPageIndex(index);
        Sort sort ;
        if (param.equals("increase"))
            sort = Sort.by(Sort.Direction.ASC, "numberScore");
        else
            sort = Sort.by(Sort.Direction.DESC, "numberScore");
        paging.setSort(sort);
        pagingRequest.setPageSize(size);
        pagingRequest.setPageIndex(index);
//        pagingRequest.set
        List<ScoreStatistic> responses = service.getScoreStatisticInTest(testId);
        return new ResponseEntity<>(
                new Response(false, responses),
                HttpStatus.OK
        );
    }
}
