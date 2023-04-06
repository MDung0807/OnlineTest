package com.iotstar.onlinetest.services.blackList;

public interface BlackListService {
    public void save(String token);

    public boolean exitByJWT(String token);

}
