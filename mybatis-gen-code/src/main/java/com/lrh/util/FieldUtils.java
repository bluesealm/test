package com.lrh.util;

import com.lrh.common.JavaType;
import org.apache.commons.lang3.StringUtils;


/**
 * @author lironghui
 * @version 1.0
 * @date 2019/7/13 15:24
 */
public class FieldUtils {
    /**
     * SQL数据类型到Java数据类型的转换
     * @param sqlType SQL数据类型
     * @return Java数据类型
     */
    public static JavaType sqlTypeToJava(String sqlType) {
        JavaType newType;
        if (sqlType.indexOf("(")!=-1){
            sqlType=sqlType.substring(0,sqlType.indexOf("("));
        }
        if (StringUtils.equalsIgnoreCase(sqlType,"varchar")) {
            newType = JavaType.String;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"char")) {
            newType = JavaType.String;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"blob")) {
            newType = JavaType.ByteArr;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"text")) {
            newType = JavaType.String;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"longtext")) {
            newType = JavaType.String;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"integer")) {
            newType = JavaType.Integer;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"tinyint")) {
            newType =JavaType.Byte;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"smallint")) {
            newType = JavaType.String;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"mediumint")) {
            newType = JavaType.Integer;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"bigint")) {
            newType = JavaType.Long;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"float")) {
            newType = JavaType.Float;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"double")) {
            newType = JavaType.Double;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"decimal")) {
            newType = JavaType.BigDecimal;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"date")) {
            newType = JavaType.Date;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"time")) {
            newType = JavaType.Date;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"datetime")) {
            newType = JavaType.Date;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"timestmp")) {
            newType =JavaType.Date;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"year")) {
            newType = JavaType.Date;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"int")) {
            newType = JavaType.Integer;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"int unsigned")) {
            newType =JavaType.Integer;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"bigint unsigned")) {
            newType = JavaType.Long;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"tinyint")) {
            newType = JavaType.Byte;
        } else if (StringUtils.equalsIgnoreCase(sqlType,"tinyint unsigned")) {
            newType =JavaType.Byte;;
        } else {
            newType = JavaType.String;
        }
        return newType;
    }

    /**
     * SQL数据类型到Java数据类型的转换
     * @param sqlType SQL数据类型
     * @return Java数据类型
     */
    public static String jdbcType(String sqlType) {
        String jdbcType;
        if (sqlType.indexOf("(")!=-1){
            sqlType=sqlType.substring(0,sqlType.indexOf("("));
        }
        if (StringUtils.equalsIgnoreCase(sqlType,"varchar")) {
            jdbcType = "VARCHAR";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"char")) {
            jdbcType = "CHAR";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"blob")) {
            jdbcType = "BLOB";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"longtext")) {
            jdbcType = "LONGVARCHAR";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"text")) {
            jdbcType = "LONGVARCHAR";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"integer")) {
            jdbcType = "INTEGER";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"tinyint")) {
            jdbcType = "TINYINT";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"smallint")) {
            jdbcType = "SMALLINT";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"mediumint")) {
            jdbcType = "MEDIUMINT";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"bigint")) {
            jdbcType = "BIGINT";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"float")) {
            jdbcType = "FLOAT";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"double")) {
            jdbcType = "DOUBLE";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"decimal")) {
            jdbcType = "DECIMAL";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"date")) {
            jdbcType = "DATE";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"time")) {
            jdbcType = "TIME";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"datetime")) {
            jdbcType = "TIMESTAMP";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"timestmp")) {
            jdbcType = "TIMESTAMP";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"year")) {
            jdbcType = "YEAR";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"int")) {
            jdbcType = "INTEGER";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"int unsigned")) {
            jdbcType = "INTEGER";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"bigint unsigned")) {
            jdbcType = "BIGINT";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"tinyint")) {
            jdbcType = "TINYINT";
        } else if (StringUtils.equalsIgnoreCase(sqlType,"tinyint unsigned")) {
            jdbcType = "TINYINT";
        } else {
            jdbcType = sqlType;
        }
        return jdbcType;
    }

    public static String upperFirstCase(String str){
        if (StringUtils.isAllBlank(str)){
            return "";
        }
       return str.substring(0, 1).toUpperCase()+str.substring(1);
    }

    public static String underLineToCamel(String str){
        String[] strArr=StringUtils.split(str,"_");
        StringBuilder sb=new StringBuilder();
        sb.append(strArr[0]);
        for (int i = 1; i <strArr.length ; i++) {
            sb.append(upperFirstCase(strArr[i]));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(underLineToCamel("s_create_user"));
    }
}
