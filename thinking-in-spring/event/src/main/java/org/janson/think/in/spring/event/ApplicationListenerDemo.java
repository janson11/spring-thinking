package org.janson.think.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Description: {@link org.springframework.context.ApplicationListener} 示例
 * @Author: Janson
 * @Date: 2020/9/8 19:55
 **/
@EnableAsync
public class ApplicationListenerDemo implements ApplicationEventPublisherAware {

    public static void main(String[] args) {
//        GenericApplicationContext context = new GenericApplicationContext();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 将引导类ApplicationListenerDemo作为Configuration Class
        context.register(ApplicationListenerDemo.class);
        // 方法一：基于Spring接口：向Spring应用上下文注册事件
        // a 方法：基于ConfigurableApplicationContext API实现
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                println("ApplicationListener—接收到Spring事件：" + event);
            }
        });
        // b方法 :基于ApplicationListener注册Spring Bean
        // 通过Configuration Class来注册
        context.register(MyApplicationListener.class);

        // 启动 Spring应用上下文 ContextRefreshedEvent
        context.refresh();
        // 启动 Spring上下文 ContextStartedEvent
        context.start();
        // 提出Spring上下文 ContextStoppedEvent
        context.stop();
        // 关闭 Spring应用上下文 ContextClosedEvent
        context.close();
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        applicationEventPublisher.publishEvent(new ApplicationEvent("Hello,World") {
        });
        applicationEventPublisher.publishEvent("Hello,World");
    }


    static class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            println("MyApplicationListener—接收到Spring事件：" + event);
        }
    }

    @EventListener
    @Order(1)
    //越小越先执行
    public void onApplicationEvent(ContextRefreshedEvent event) {
        println("@EventListener(onApplicationEvent2)—接收到Spring ContextRefreshedEvent");
    }

    @EventListener
    @Order(2)
    public void onApplicationEvent1(ContextRefreshedEvent event) {
        println("@EventListener(onApplicationEvent1)—接收到Spring ContextRefreshedEvent");
    }

    @EventListener
    @Async
    public void onApplicationEventAsync(ContextRefreshedEvent event) {
        println("@EventListener—接收到Spring ContextRefreshedEvent");
    }

    private static void println(Object printable) {
        System.out.printf("[线程:%s]:%s\n", Thread.currentThread().getName(), printable);
    }


}
