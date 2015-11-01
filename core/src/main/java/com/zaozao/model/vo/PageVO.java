package com.zaozao.model.vo;

import java.util.List;

/**
 * Created by luohao on 2015/11/1.
 */
public class PageVO<T> extends BaseVO {

    private int pageSize = 25; //每页条数
    private long pageNumber = 0; //页码
    private long rowCout = 0; //总记录数
    private long pageCount = 0; //总页数
    private List<T> data; //数据

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getRowCout() {
        return rowCout;
    }

    public void setRowCout(long rowCout) {
        this.rowCout = rowCout;
    }

    public long getPageCount() {
        if(pageCount == 0){
            pageCount = rowCout % pageSize + (rowCout / pageSize > 0 ? 1 : 0);
        }
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
