package com.fourous.gitbuild.base.constants;

/**
 * @author fourous
 * @date: 2019/11/1
 * @description: 定时任务状态
 */
public enum TimedTaskStatus {
    RUNNING("RUNNING"),

    STOP("STOP");

    private String status;

    TimedTaskStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
