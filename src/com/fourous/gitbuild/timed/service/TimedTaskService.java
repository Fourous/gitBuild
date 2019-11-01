package com.fourous.gitbuild.timed.service;

import com.fourous.gitbuild.timed.TimedTask;
import org.quartz.SchedulerException;

import java.io.IOException;
import java.util.List;

/**
* @author fourous
* @date: 2019/11/1
* @description: 定时任务服务
*/
public interface TimedTaskService {
    /**
     * 新增定时任务
     * @param task
     * @return
     * @throws IOException
     * @throws SchedulerException
     */
    boolean addTimedTask(TimedTask task) throws IOException, SchedulerException;

    /**
     * 获取所有的定时任务
     * @return
     */
    List<TimedTask> getTimedTasks();

    /**
     * 获取定时任务
     * @param id
     * @return
     */
    TimedTask getTimedTask(String id);

    /**
     * 设置定时任务的状态
     * @param id
     * @param status
     * @return
     */
    boolean setStatus(String id,String status);

    /**
     * 运行定时任务
     * @param task
     * @return
     * @throws SchedulerException
     */
    boolean startTimedTask(TimedTask task) throws SchedulerException;

    /**
     * 删除定时器
     * @param id
     * @return
     * @throws SchedulerException
     */
    boolean deleteTimedTask(String id) throws SchedulerException;

    /**
     * 停止定时器
     * @param id
     * @return
     * @throws SchedulerException
     */
    boolean stopTimedTask(String id) throws SchedulerException;

    /**
     * 启动定时器
     * @param id
     * @return
     * @throws SchedulerException
     */
    boolean startTimedTask(String id) throws SchedulerException;
}
