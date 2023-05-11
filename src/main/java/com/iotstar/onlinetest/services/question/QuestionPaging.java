package com.iotstar.onlinetest.services.question;

import com.iotstar.onlinetest.common.paging.PagingRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class QuestionPaging extends PagingRequest {
    @Override
    public void setPageIndex(int pageIndex) {
        super.setPageIndex(pageIndex);
    }

    @Override
    public void setPageSize(int pageSize) {
        super.setPageSize(pageSize);
    }

    @Override
    public int getPageNumber() {
        return super.getPageNumber();
    }

    @Override
    public int getPageSize() {
        return super.getPageSize();
    }

    public Pageable pageable (){
        return PageRequest.of(getPageNumber(), getPageSize());
    }
}
