package com.lrh.mybatis.pagehelper.dialect;

import com.lrh.mybatis.pagehelper.Dialect;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/10/7 11:28
 */
public class Mysql implements Dialect {
    @Override
    public String buildCountSQL(String originalSQL) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT COUNT(1) AS TOTAL FROM ( ").append(originalSQL).append(" ) AS T ");
        return sb.toString();
    }

    @Override
    public String buildPageSQL(String originalSQL, int offset, int limit) {
        StringBuilder sb = new StringBuilder();
        sb.append(originalSQL).append(" LIMIT ").append(offset).append(" , ").append(limit);
        return sb.toString();
    }
}
