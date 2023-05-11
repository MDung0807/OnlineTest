package com.iotstar.onlinetest.common.paging;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PagingRequest implements Pageable {
    private int pageIndex=0;
    private int pageSize=10;
    private Sort sort;

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Pageable pageable(){
        return PageRequest.of(getPageNumber(), getPageSize());
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }

    @Override
    public int getPageNumber() {
        return pageIndex;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public long getOffset() {
        return 0;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
