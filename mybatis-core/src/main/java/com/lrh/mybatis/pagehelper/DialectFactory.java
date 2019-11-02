package com.lrh.mybatis.pagehelper;

import com.lrh.mybatis.pagehelper.dialect.Mysql;
import com.lrh.mybatis.pagehelper.dialect.Oracle;
import org.apache.ibatis.annotations.Case;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/10/7 12:37
 */
public class DialectFactory {
    public static Dialect buildDialect(String type) {
        if (type == null || type.length() == 0) {
            return new Mysql();
        }
        if (type.toLowerCase().equals("oracle")) {
            return new Oracle();
        } else {
            return new Mysql();
        }
    }
}
