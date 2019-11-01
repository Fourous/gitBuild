package com.fourous.gitbuild.base.listener;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Collection;

/**
* @author fourous
* @date: 2019/11/1
* @description: 定时任务监听器
*/
public class TimedTaskListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent){

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent){
        try{
            Collection<Scheduler> allSchedulers = new StdSchedulerFactory().getAllSchedulers();
            for(Scheduler scheduler:allSchedulers){
                scheduler.shutdown();
                System.out.println("停止定时器"+scheduler.getSchedulerName());
            }
        }catch (SchedulerException e){
            e.printStackTrace();
        }
    }
}
