package org.janson.think.in.spring.application.context.lifecycle;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.io.IOException;

/**
 * @Description: Spring Shutdown Hook 线程示例
 * @Author: Janson
 * @Date: 2020/9/22 23:36
 **/
public class SpringShutdownHookThreadDemo {

    public static void main(String[] args) throws IOException {
        GenericApplicationContext context = new GenericWebApplicationContext();

        context.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
            @Override
            public void onApplicationEvent(ContextClosedEvent event) {
                System.out.printf("[线程 %s] ContextClosedEvent 处理\n", Thread.currentThread().getName());
            }
        });
        // 刷新Spring应用上下文
        context.refresh();
        // 启动Spring应用上下文

        // 注冊Shutdown Hook
        context.registerShutdownHook();

        System.out.println("按任意键继续并且关闭Spring应用上下文");
        System.in.read();
        // 关闭Spring应用上下文(同步)
        context.close();
    }
}
