package com.lrh.common;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/7/21 10:04
 */
public enum JavaType {
    Byte("Byte", ""),
    Integer("Integer", ""),
    Long("Long", ""),
    Float("Float", ""),
    Double("Double", ""),
    String("String", ""),
    Date("Date", "java.util.Date"),
    BigDecimal("BigDecimal", "java.math.BigDecimal"),
    LocalDate("LocalDate", "java.time.LocalDate"),
    LocalTime("LocalTime", "java.time.LocalTime"),
    ByteArr("byte[]", ""),;
    private String name;
    private String desc;

    JavaType(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }
}
