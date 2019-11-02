package com.lrh.mybatis.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/7/21 15:44
 */
@SuppressWarnings("all")
public class ParameterUtil {
    public static Map<String, Object> converObjToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (int i = 0, len = fields.length; i < len; i++) {
                String varName = fields[i].getName();
                boolean accessFlag = fields[i].isAccessible();
                fields[i].setAccessible(true);// 允许通过反射访问该字段
                Object valueObj = fields[i].get(obj);
                if (valueObj != null) {
                    map.put(varName, valueObj);
                }
                fields[i].setAccessible(accessFlag);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    public static Map<String, Object> converObjNotEmptyToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            for (int i = 0, len = fields.length; i < len; i++) {
                String varName = fields[i].getName();
                boolean accessFlag = fields[i].isAccessible();
                fields[i].setAccessible(true);// 允许通过反射访问该字段
                Object valueObj = fields[i].get(obj);
                if (valueObj != null && !"".equals(valueObj)) {
                    map.put(varName, valueObj);
                }
                fields[i].setAccessible(accessFlag);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
