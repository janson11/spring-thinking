package org.janson.think.in.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @Description: 注入
 * @Author: Janson
 * @Date: 2020/9/9 20:54
 **/
public class InjectingApplicationEventPublisherDemo implements ApplicationEventPublisherAware, ApplicationContextAware, BeanPostProcessor {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        // #3
        applicationEventPublisher.publishEvent(new MySpringEvent("The event from @Autowired ApplicationEventPublisher"));
        // #4
        applicationContext.publishEvent(new MySpringEvent("The event from @Autowired ApplicationContext"));
    }

    public static void main(String[] args) {
        // 1、创建Spring应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 2、注册Configuration Class
        context.register(InjectingApplicationEventPublisherDemo.class);
        // 3、增加Spring 事件监听器
        context.addApplicationListener(new MySpringEventListener());
        // 4、启动Spring应用上下文
        context.refresh();
        // 5、关闭Spring应用上下文
        context.close();
    }


    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        // #1
        applicationEventPublisher.publishEvent(new MySpringEvent("The event from ApplicationEventPublisherAware"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // #2
        applicationContext.publishEvent(new MySpringEvent("The event from ApplicationContextAware"));
    }
}
