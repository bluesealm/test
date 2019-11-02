package com.lrh.mybatis.pagehelper;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/10/7 11:25
 */
public interface Dialect {
    // 构建Count的SQL语句
    public String buildCountSQL(String originalSQL);

    // 构建分页的SQL语句
    String buildPageSQL(String originalSQL, int offset, int limit);
}
