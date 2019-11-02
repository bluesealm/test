package com.lrh.entity;

import com.lrh.config.Strategy;
import com.lrh.rule.PropertyRule;
import com.lrh.util.FieldUtils;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/7/13 15:43
 */
public class TableField {
    private String filedName;
    private String filedType;
    private String comment;
    private boolean primary;
    private boolean autoIncrement;
    private String jdbcType;
    private String propertyName;
    private String propertyType;
    private String getMethodName;
    private String setMethodName;

    public void init(Strategy strategy){
        if (strategy.getPropertyRule().equals(PropertyRule.under_line_to_camel)) {
            this.propertyName=(FieldUtils.underLineToCamel(filedName));
        }else {
            this.propertyName=filedName;
        }
        this.propertyType=FieldUtils.sqlTypeToJava(filedType).getName();
        this.getMethodName="get" + FieldUtils.upperFirstCase(propertyName);
        this.setMethodName="set" + FieldUtils.upperFirstCase(propertyName);
    }

    public String getFiledName() {
        return filedName;
    }

    public void setFiledName(String filedName) {
        this.filedName = filedName;
    }

    public String getFiledType() {
        return filedType;
    }

    public void setFiledType(String filedType) {
        this.filedType = filedType;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public String getGetMethodName() {
        return getMethodName;
    }

    public void setGetMethodName(String getMethodName) {
        this.getMethodName = getMethodName;
    }

    public String getSetMethodName() {
        return setMethodName;
    }

    public void setSetMethodName(String setMethodName) {
        this.setMethodName = setMethodName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }
}
