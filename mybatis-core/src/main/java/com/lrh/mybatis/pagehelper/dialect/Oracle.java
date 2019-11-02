package com.lrh.mybatis.pagehelper.dialect;

import com.lrh.mybatis.pagehelper.Dialect;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/10/7 11:33
 *
 */
//TODO
public class Oracle implements Dialect {
    @Override
    public String buildCountSQL(String originalSQL) {
        return null;
    }

    @Override
    public String buildPageSQL(String originalSQL, int offset, int limit) {
        return null;
    }
}
