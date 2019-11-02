package com.lrh.db;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/7/13 15:57
 */
public enum SqlQuery {
    QUERY_FIELD("SHOW FULL FIELDS FROM %s"),
    QUERY_TABLE("SHOW TABLE STATUS");
    private String sql;

    SqlQuery(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
