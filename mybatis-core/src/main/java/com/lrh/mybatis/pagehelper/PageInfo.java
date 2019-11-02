package com.lrh.mybatis.pagehelper;

import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/10/7 11:13
 */
public class PageInfo<T> {
    // 开始页(当前页)
    private int currentPage;
    // 每页的记录数
    private int pageSize;
    // 总记录数
    private int totalCount;
    // 总页数
    private int totalPage;
    // 每页的数据集合
    private List<T> obj;

    public PageInfo() {
    }

    public PageInfo(PageVO pageVO, List<T> obj) {
        setPageVO(pageVO);
        this.obj = obj;
    }

    public void setPageVO(PageVO pageVO) {
        this.currentPage = pageVO.getCurrentPage();
        this.pageSize = pageVO.getPageSize();
        this.totalCount = pageVO.getTotalCount();
        this.totalPage = pageVO.getTotalPage();
    }

    public List<T> getObj() {
        return obj;
    }

    public void setObj(List<T> obj) {
        this.obj = obj;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
