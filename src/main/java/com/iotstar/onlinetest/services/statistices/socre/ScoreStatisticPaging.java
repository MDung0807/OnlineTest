package com.iotstar.onlinetest.services.statistices.socre;

import com.iotstar.onlinetest.common.paging.PagingRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class ScoreStatisticPaging extends PagingRequest {
    private int pageIndex, pageSize;
    private Sort sort;

    @Override
    public Sort getSort() {
        return super.getSort();
    }

    public void setSort(Sort sort) {
        this.sort = sort;
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
    public Pageable pageable(){
        return PageRequest.of(getPageNumber(), getPageSize(), sort);
    }
}
