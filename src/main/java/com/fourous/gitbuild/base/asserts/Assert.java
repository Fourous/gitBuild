package main.java.com.fourous.gitbuild.base.asserts;

import main.java.com.fourous.gitbuild.base.exception.FileNotFoundException;
import main.java.com.fourous.gitbuild.util.FileUtil;

import javax.servlet.FilterChain;

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
