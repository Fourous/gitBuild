package main.java.com.fourous.gitbuild.base.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextException;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fourous
 * @date: 2019/10/31
 * @description: 获取bean
 */
public class BeanDefinedLocator implements ApplicationContextAware {
    private static final BeanDefinedLocator beanLocator = new BeanDefinedLocator();
    private ApplicationContext context = null;

    private BeanDefinedLocator() {
    }

    public static BeanDefinedLocator getInstance() {
        return beanLocator;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        beanLocator.context = applicationContext;
    }

    public Object getBean(String beanName) {
        Assert.hasText(beanName);
        return this.context.getBean(beanName);
    }

    /**
     * 根据指定的beans名及类型获取bean
     *
     * @param beanName
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(String beanName, Class<T> clazz) {
        Assert.hasText(beanName);
        contextNotNull();
        return (T) this.context.getBean(beanName, clazz);
    }

    /**
     * 根据指定的类型来获取bean
     *
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> clazz) {
        Assert.notNull(clazz);
        contextNotNull();
        return (T) BeanFactoryUtils.beansOfTypeIncludingAncestors(this.context, clazz).values().iterator().next();
    }

    /**
     * 根据指定类型类获取bean集合
     *
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> getBeans(Class<T> clazz) {
        Assert.notNull(clazz);
        contextNotNull();
        return new ArrayList<T>(BeanFactoryUtils.beansOfTypeIncludingAncestors(this.context, clazz).values());
    }

    private void contextNotNull() {
        if (this.context == null) {
            throw new ApplicationContextException("application context is null");
        }
    }
}
