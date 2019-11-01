package com.fourous.gitbuild.timed;
/**
* @author fourous
* @date: 2019/11/1
* @description: 定时任务手动配置VO在这里配置所以有关信息
*/
public class TimedTask {
    private String id;
    /**
     * CronExpression表达式，用于设置定时规则
     */
    private String expression;
    private String gitUrl;
    private String gitName;
    private String gitPassword;
    /**
     * 部署路径，也就是Tomcat路径
     */
    private String deployPath;
    private String status;
    /**
     * 构建方式，debug模式，package模式，deploy模式
     */
    private String buildMode;
    /**
     * 一些端口配置
     */
    private String tomcatServerPort;
    private String tomcatServerHTTPPort;
    private String tomcatServerAJPPort;

    /**
     * Getter Setter方法
     * @return
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public String getGitName() {
        return gitName;
    }

    public void setGitName(String gitName) {
        this.gitName = gitName;
    }

    public String getGitPassword() {
        return gitPassword;
    }

    public void setGitPassword(String gitPassword) {
        this.gitPassword = gitPassword;
    }

    public String getDeployPath() {
        return deployPath;
    }

    public void setDeployPath(String deployPath) {
        this.deployPath = deployPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuildMode() {
        return buildMode;
    }

    public void setBuildMode(String buildMode) {
        this.buildMode = buildMode;
    }

    public String getTomcatServerPort() {
        return tomcatServerPort;
    }

    public void setTomcatServerPort(String tomcatServerPort) {
        this.tomcatServerPort = tomcatServerPort;
    }

    public String getTomcatServerHTTPPort() {
        return tomcatServerHTTPPort;
    }

    public void setTomcatServerHTTPPort(String tomcatServerHTTPPort) {
        this.tomcatServerHTTPPort = tomcatServerHTTPPort;
    }

    public String getTomcatServerAJPPort() {
        return tomcatServerAJPPort;
    }

    public void setTomcatServerAJPPort(String tomcatServerAJPPort) {
        this.tomcatServerAJPPort = tomcatServerAJPPort;
    }
}
