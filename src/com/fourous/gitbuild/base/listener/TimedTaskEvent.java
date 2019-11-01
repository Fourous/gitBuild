package com.fourous.gitbuild.base.listener;

import com.fourous.gitbuild.base.constants.TimedTaskStatus;
import com.fourous.gitbuild.base.spring.BeanDefinedLocator;
import com.fourous.gitbuild.timed.TimedTask;
import com.fourous.gitbuild.timed.service.TimedTaskService;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fourous
 * @date: 2019/11/1
 * @description: 定时任务监听器
 */
@Service
public class TimedTaskEvent implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        TimedTaskService timedTaskService = BeanDefinedLocator.getInstance().getBean(TimedTaskService.class);
        List<TimedTask> timedTasks = timedTaskService.getTimedTasks();
        for (TimedTask timedTask : timedTasks) {
            if (TimedTaskStatus.RUNNING.getStatus().equals(timedTask.getStatus())) {
                try {
                    System.out.println("启动定时器" + timedTask.getId());
                    timedTaskService.startTimedTask(timedTask);
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
