package com.lrh.mybatis.pagehelper;

import org.apache.ibatis.session.RowBounds;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/10/7 12:45
 */
public class PageVO extends RowBounds {
    private static final int DEFAULT_CURRENT_PAGE = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    // 开始页(当前页)
    private int currentPage = DEFAULT_CURRENT_PAGE;
    // 每页的记录数
    private int pageSize = DEFAULT_PAGE_SIZE;
    // 总记录数
    private int totalCount;
    // 总页数
    private int totalPage;


    public PageVO() {
        super(1,10);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage > 0) {
            this.currentPage = currentPage;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        if (pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.totalPage = this.totalCount > 0 && this.pageSize > 0 ? (this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1) : 0;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

}
