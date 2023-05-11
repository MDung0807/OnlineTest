package com.iotstar.onlinetest.services.review;

import com.iotstar.onlinetest.common.paging.PagingRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ReviewPaging extends PagingRequest {
    @Override
    public void setSort(Sort sort) {
        super.setSort(sort);
    }

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

    @Override
    public Sort getSort() {
        return super.getSort();
    }

    public Pageable pageable (){
        return PageRequest.of(getPageNumber(), getPageSize());
    }
}
