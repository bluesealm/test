package com.lrh.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author lironghui
 * @version 1.0
 * @date 2019/7/21 15:03
 */
public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static String packageToPath(String packageName) {
        if (StringUtils.isEmpty(packageName)) {
            return File.separator;
        }
        String[] strArr = StringUtils.split(packageName, ".");
        StringBuilder sb = new StringBuilder(File.separator);
        for (String str : strArr) {
            sb.append(str).append(File.separator);
        }
        return sb.substring(0,sb.length()-1);
    }
}
