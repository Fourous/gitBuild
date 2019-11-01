package com.fourous.gitbuild.timed.service.impl;

import com.fourous.gitbuild.base.constants.TimedTaskStatus;
import com.fourous.gitbuild.system.ConfigManager;
import com.fourous.gitbuild.timed.TimedTask;
import com.fourous.gitbuild.timed.job.BuildJob;
import com.fourous.gitbuild.timed.service.TimedTaskService;
import com.fourous.gitbuild.util.FileUtil;
import com.fourous.gitbuild.util.JsonUtil;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author fourous
 * @date: 2019/11/1
 * @description: 定时任务实现
 */
@Service
public class TimedTaskServiceImpl implements TimedTaskService {
    @Override
    public boolean addTimedTask(TimedTask task) throws IOException, SchedulerException {
        String timeTaskPath = ConfigManager.getSystemProperty("buildPath") + File.separator + "timed" + File.separator + task.getId();
        FileUtil.newFile(timeTaskPath + File.separator + "timedTask.json", JsonUtil.toJson(task));
        startTimedTask(task);
        setStatus(task.getId(), TimedTaskStatus.RUNNING.getStatus());
        return true;
    }

    @Override
    public List<TimedTask> getTimedTasks() {
        String timedTaskRootPath = ConfigManager.getSystemProperty("buildPath") + File.separator + "timed";
        File timedTaskRootFile = new File(timedTaskRootPath);
        List<TimedTask> allTasks = new ArrayList<>();
        if(timedTaskRootFile.exists() && timedTaskRootFile.isDirectory()){
            File[] taskPaths = timedTaskRootFile.listFiles();
            for(File taskPath:taskPaths){
                File file = new File(taskPath,"timedTask.json");
                String taskInfoJson = FileUtil.getFileContent(file.getAbsolutePath());
                TimedTask buildTask = JsonUtil.toObject(taskInfoJson,TimedTask.class);
                allTasks.add(buildTask);
            }
        }
        return allTasks;
    }

    @Override
    public TimedTask getTimedTask(String id) {
        String timedTaskPath = ConfigManager.getSystemProperty("buildPath")+File.separator+"timed"+File.separator+id;

        File timedTaskFile = new File(timedTaskPath,"timedTask.json");
        String taskInfoJson = FileUtil.getFileContent(timedTaskFile.getAbsolutePath());
        return JsonUtil.toObject(taskInfoJson,TimedTask.class);
    }

    @Override
    public boolean setStatus(String id, String status) {
        TimedTask timedTask = getTimedTask(id);
        timedTask.setStatus(status);
        String timeTaskPath = ConfigManager.getSystemProperty("buildPath")+File.separator+"timed"+File.separator+id;
        FileUtil.writeFile(timeTaskPath+ File.separator+"timeTask.json",JsonUtil.toJson(timedTask));
        return true;
    }

    @Override
    public boolean startTimedTask(TimedTask task) throws SchedulerException {
        JobDetail buildJobDetail = JobBuilder.newJob(BuildJob.class).withIdentity(new JobKey(task.getId())).build();
        JobDataMap jobDataMap = buildJobDetail.getJobDataMap();
        jobDataMap.put("id",task.getId());
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(task.getExpression()))
                .build();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.scheduleJob(buildJobDetail,cronTrigger);
        scheduler.start();
        System.out.println("启动定时器ID"+task.getId());
        return true;
    }

    @Override
    public boolean deleteTimedTask(String id) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        boolean deleteJob = scheduler.deleteJob(new JobKey(id));
        System.out.printf("定时器删除id"+deleteJob);
        String timeTaskPath = ConfigManager.getSystemProperty("buildPath")+File.separator+"timed"+File.separator+id;
        FileUtil.delete(new File(timeTaskPath));
        return false;
    }

    @Override
    public boolean stopTimedTask(String id) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        boolean deleteJob = scheduler.deleteJob(new JobKey(id));
        System.out.println("定时器删除ID"+deleteJob);
        setStatus(id,TimedTaskStatus.STOP.getStatus());
        return true;
    }

    @Override
    public boolean startTimedTask(String id) throws SchedulerException {
        TimedTask timedTask = getTimedTask(id);
        startTimedTask(timedTask);
        setStatus(id,TimedTaskStatus.RUNNING.getStatus());
        return true;
    }
}
