package com.iotstar.onlinetest.services.statistices.test;

import com.iotstar.onlinetest.common.paging.PagingRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class TestStatisticPaging extends PagingRequest {
    private int pageIndex, pageSize;
    private Sort sort;
    @Override
    public int getPageNumber() {
        return pageIndex;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public Sort getSort() {
        return this.sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Pageable pageable (){
        return PageRequest.of(pageIndex, pageSize, sort);
    }
}
