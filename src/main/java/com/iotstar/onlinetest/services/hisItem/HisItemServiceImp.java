package com.iotstar.onlinetest.services.hisItem;

import com.iotstar.onlinetest.exceptions.ResourceNotFoundException;
import com.iotstar.onlinetest.models.HisItem;
import com.iotstar.onlinetest.repositories.HisItemDAO;
import com.iotstar.onlinetest.repositories.HistoryDAO;
import com.iotstar.onlinetest.utils.AppConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HisItemServiceImp implements HisItemService{

    @Autowired
    private HisItemDAO hisItemDAO;
    @Override
    public HisItem getById(Long id) {
        return hisItemDAO.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(AppConstant.NOT_FOUND)
        );
    }
}
