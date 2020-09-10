package org.janson.think.in.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
 * @Description: 异步时间处理器示例
 * @Author: Janson
 * @Date: 2020/9/10 20:10
 **/
public class AsyncEventListenerHandlerDemo {

    public static void main(String[] args) {
        // 1、创建Spring应用上下文
        GenericApplicationContext context = new GenericApplicationContext();
        // 2、增加Spring 事件监听器
        context.addApplicationListener(new MySpringEventListener());
        // 3、启动Spring应用上下文
        context.refresh();
        // 初始化ApplicationEventMulticaster
        ApplicationEventMulticaster applicationEventMulticaster = context.getBean(AbstractApplicationContext.APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
        // 判断当前ApplicationEventMulticaster是否为SimpleApplicationEventMulticaster
        if (applicationEventMulticaster instanceof SimpleApplicationEventMulticaster) {
            SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = (SimpleApplicationEventMulticaster) applicationEventMulticaster;
            // 切换taskExecutor
            ExecutorService executorService = newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-thread-pool"));
            // 同步—>异步
            simpleApplicationEventMulticaster.setTaskExecutor(executorService);
            // 添加ContextClosedEvent
            applicationEventMulticaster.addApplicationListener((ApplicationListener<ContextClosedEvent>) event -> {
                if (!executorService.isShutdown()) {
                    executorService.shutdown();
                }
            });
        }
        context.publishEvent(new MySpringEvent("Hello world"));
        // 4、关闭Spring应用上下文
        context.close();
    }
}
