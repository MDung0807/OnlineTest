package com.iotstar.onlinetest.services.statistices.test;

import com.iotstar.onlinetest.DTOs.responses.statistices.TestStatisticResponse;
import com.iotstar.onlinetest.DTOs.responses.statistices.TestStatisticResponseUser;
import com.iotstar.onlinetest.models.Test;
import com.iotstar.onlinetest.models.statistics.TestStatistic;
import com.iotstar.onlinetest.repositories.HistoryDAO;
import com.iotstar.onlinetest.repositories.TestDAO;
import com.iotstar.onlinetest.services.test.TestServiceImp;
import com.iotstar.onlinetest.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TestStatisticServiceImp extends TestStatisticPaging implements TestStatisticService {

    @Autowired
    private TestServiceImp testServiceImp;
    @Autowired
    private HistoryDAO historyDAO;
    @Autowired
    private Converter converter;
    @Autowired
    private TestDAO testDAO;
    @Override
    public List<TestStatisticResponse> getTestStatistic() {
        //Get data from database
        List<Map<String, Object>> data = testDAO.TestStatistics(pageable());
       //Convert data to TestStatistic
        List<TestStatistic> testStatistics = returnTestStatic(data);
       //Create object response
        List<TestStatisticResponse>responses = new ArrayList<>();

        for (TestStatistic i: testStatistics) {
           TestStatisticResponse response = new TestStatisticResponse();
           response.setNumberUserTest(i.getNumberUserTest());
           Test test = testServiceImp.getTestReturnTest(i.getTestId());
           response.setTestName(test.getTestName());
           response.setTestId(test.getTestId());
           responses.add(response);
       }
        return responses;
    }

    private List<TestStatistic> returnTestStatic(List<Map<String, Object>> params){
        List<TestStatistic> testStatistics = new ArrayList<>();
        for (Map<String, Object> i: params){
            testStatistics.add(converter.converterTestStatistic(i));
        }
        return testStatistics;
    }

    @Override
    public List<TestStatisticResponseUser> getUserFinishTest(Long testId) {
        List<Map<String,Object>> data =historyDAO.getTestStatistic(testId, pageable());
        List<TestStatisticResponseUser> responses = new ArrayList<>();
        for (Map<String,Object>i: data){
            responses.add(converter.converterTestStatisticUser(i));
        }
        return responses;
    }
}
