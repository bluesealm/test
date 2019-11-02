package com.lrh.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/7/13 15:24
 */
public class DateUtils {

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String currentDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(currentTime);
    }
    /**
     * 获取当前时间
     *
     * @return
     */
    public static String currentTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(currentTime);
    }

    public static void main(String[] args) {
        System.out.println(currentTime());
    }
}
