package com.iotstar.onlinetest.services.blackList;


import com.iotstar.onlinetest.models.BlackList;
import com.iotstar.onlinetest.repositories.BlackListDAO;
import com.iotstar.onlinetest.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BackListServiceImp implements BlackListService{
    @Autowired
    private BlackListDAO blackListDAO;
    @Value("${test.app.jwtExpirationMs}")
    private Long time;

    private BlackList blackList;

    @Override
    public void save(String token) {
        blackList = new BlackList();
        blackList.setJwt(token);
        blackList.setDate(LocalDateTime.now());
        blackListDAO.save(blackList);
    }

    @Override
    public boolean exitByJWT(String token){
        return blackListDAO.existsByJwt(token);
    }

}
