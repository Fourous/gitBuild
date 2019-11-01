package com.fourous.gitbuild.timed.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fourous
 * @date: 2019/11/1
 * @description: maven编译阶段
 */
public class BuildJob implements Job {
    private static Logger logger = LoggerFactory.getLogger(BuildJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            /**
             * 处理任务的主要方法，调用BuildService来进行配置构建
             * TODO 处理BuildService构建
             */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
