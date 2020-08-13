package org.janson.think.in.spring.bean.lifecycle;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Description: User Holder 类
 * @Author: Janson
 * @Date: 2020/8/10 23:29
 **/
public class UserHolder implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, EnvironmentAware, InitializingBean
        , SmartInitializingSingleton,DisposableBean {
    private final User user;
    private Integer number;
    private String description;

    private ClassLoader classLoader;
    private BeanFactory beanFactory;
    private String beanName;
    private Environment environment;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * 依赖注解驱动
     * 当前场景：BeanFactory
     */
    @PostConstruct
    public void initPostConstruct() {
        // postProcessBeforeInitialization V3 ->V4
        this.description = "The user holder V4";
        System.out.println("initPostConstruct()=" + description);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // init() V3 ->V4
        this.description = "The user holder V5";
        System.out.println("afterPropertiesSet()=" + description);
    }

    public void init() {
        // afterPropertiesSet V5 ->V6
        this.description = "The user holder V6";
        System.out.println("init()=" + description);
    }


    @PreDestroy
    public void preDestroy() {
        // postProcessBeforeDestruction V9 ->V10
        this.description = "The user holder V10";
        System.out.println("preDestroy()=" + description);
    }

    @Override
    public void destroy() throws Exception {
        // preDestroy V10 ->V11
        this.description = "The user holder V11";
        System.out.println("destroy()=" + description);
    }

    public void doDestroy(){
        // destroy V11 ->V12
        this.description = "The user holder V12";
        System.out.println("doDestroy()=" + description);
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    public UserHolder(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void afterSingletonsInstantiated() {
        // postProcessAfterInitialization V7
        this.description = "The user holder V8";
        System.out.println("afterSingletonsInstantiated()=" + description);
    }
}
