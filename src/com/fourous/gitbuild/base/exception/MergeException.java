package com.fourous.gitbuild.base.exception;

/**
 * @author fourous
 * @date: 2019/10/31
 * @description: JSON合并报错
 */
public class MergeException extends RuntimeException {
    public MergeException(String msg) {
        super(msg);
    }
}
