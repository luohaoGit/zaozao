package com.zaozao.model.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by luohao on 2015/11/1.
 */
public class PageVO<T> extends BaseVO {

    private int pageSize = 10; //每页条数
    private int pageNumber = 0; //页码
    private long rowCount = 0; //总记录数
    private long pageCount = 0; //总页数
    private List<T> data; //数据
    private long showCount = 8;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate = new Date();
    private String id;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public long getRowCount() {
        return rowCount;
    }

    public void setRowCount(long rowCount) {
        this.rowCount = rowCount;
    }

    public long getPageCount() {
        return rowCount / pageSize + (rowCount % pageSize > 0 ? 1 : 0);
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public long getShowCount() {
        return this.showCount;
    }

    public void setShowCount(long showCount) {
        this.showCount = showCount;
    }

    public long getStartIndex(){
        long pre = this.pageNumber+1 - this.showCount/2;
        return pre > 0 ? pre : 1;
    }

    public long getEndIndex(){
        long remaining = this.getPageCount() - this.getStartIndex();
        return this.getStartIndex() - 1 + (remaining >= this.showCount ? this.showCount : remaining+1);
    }

    public boolean isFirst(){
        return this.pageNumber == 0;
    }

    public boolean isLast(){
        return this.pageNumber == this.getPageCount()-1;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Date getStartDate() {
        if(this.startDate == null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH, -1);
            return calendar.getTime();
        }else{
            return startDate;
        }
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
