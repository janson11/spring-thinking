package org.janson.think.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description: 自定义Spring事件示例
 * @Author: Janson
 * @Date: 2020/9/9 20:40
 **/
public class CustomizedSpringEventDemo {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        // 1、添加自定义Spring 事件监听器
        // ListenerRetriever -> 0..N个ApplicationListener<MySpringEvent>的实例
        context.addApplicationListener(new MySpringEventListener());
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println("Event：" + event);
            }
        });
        // 2、启动应用上下文
        context.refresh();
        // 3、发布自定义Spring事件
        // ListenerCacheKey ->MySpringEvent
        context.publishEvent(new MySpringEvent("Hello,World"));
        context.publishEvent(new MySpringEvent2("Hello2,World"));
        // 4、关闭Spring应用上下文
        context.close();
    }
}
