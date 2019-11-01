package com.fourous.gitbuild.base.exception;

/**
 * @author fourous
 * @date: 2019/10/31
 * @description: 文件未发现异常
 */
public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException(String msg) {
        super(msg);
    }
}
