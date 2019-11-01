package com.fourous.gitbuild.base.asserts;

import com.fourous.gitbuild.base.exception.FileNotFoundException;
import com.fourous.gitbuild.util.FileUtil;
/**
* @author fourous
* @date: 2019/11/1
* @description: 断言
*/
public class Assert {
    /**
     * 非空断言
     *
     * @param object
     * @param message
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
    }

    /**
     * 字符串非空判断
     *
     * @param check
     * @param message
     */
    public static void notNullOrEmpty(String check, String message) {
        notNull(check, message);
        if ("".equals(check)) {
            throw new NullPointerException(message);
        }
    }

    /**
     * 文件存在判断
     *
     * @param path
     * @param message
     */
    public static void exist(String path, String message) {
        if (!FileUtil.exists(path)) {
            throw new FileNotFoundException(message);
        }
    }
}
