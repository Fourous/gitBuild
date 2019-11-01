package com.fourous.gitbuild.base.constants;

/**
 * @author fourous
 * @date: 2019/10/30
 * @description: 构建方式选择
 * enum一般可以用在程序进行或者业务进行状态，模式选择等情况
 */
public enum BuildMode {
    /**
     * debug模式
     */
    DEBUG("DEBUG"),
    /**
     * 留下构建结果
     */
    PACKAGE("PACKAGE"),
    /**
     * 部署模式
     */
    DEPLOY("DEPLOY");

    private String status;

    BuildMode(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
