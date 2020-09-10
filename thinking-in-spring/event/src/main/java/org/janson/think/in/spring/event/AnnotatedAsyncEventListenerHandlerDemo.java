package org.janson.think.in.spring.event;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
 * @Description: 注解异步时间处理器示例
 * @Author: Janson
 * @Date: 2020/9/10 20:10
 **/
@EnableAsync
// 激活异步
public class AnnotatedAsyncEventListenerHandlerDemo {

    public static void main(String[] args) {
        // 1、创建Spring应用上下文
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 2、注册Configuration Class
        context.register(AnnotatedAsyncEventListenerHandlerDemo.class);
        // 4、启动Spring应用上下文
        context.refresh();
        context.publishEvent(new MySpringEvent("Hello world"));
        // 5、关闭Spring应用上下文
        context.close();
    }

    @EventListener
    @Async // 同步—>异步
    public void onEvent(MySpringEvent event) {
        System.out.printf("[线程：%s] onEvent监听事件：%s\n", Thread.currentThread().getName(), event);
    }

    @Bean
    public Executor taskExecutor() {
        ExecutorService taskExecutor = newSingleThreadExecutor(new CustomizableThreadFactory("my-spring-event-thread-pool-a"));
        return taskExecutor;
    }
}
