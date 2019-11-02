package com.lrh.rule;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/7/20 12:59
 */
public enum Swagger {
    OPEN("open"),
    CLOSE("close");

    private String value;

    Swagger(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
