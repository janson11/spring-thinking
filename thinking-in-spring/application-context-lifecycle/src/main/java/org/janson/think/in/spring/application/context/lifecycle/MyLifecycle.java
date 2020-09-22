package org.janson.think.in.spring.application.context.lifecycle;

import org.springframework.context.Lifecycle;

/**
 * @Description: 自定义 {@link Lifecycle}
 * @Author: Janson
 * @Date: 2020/9/22 23:34
 **/
public class MyLifecycle implements Lifecycle {
    private boolean running = false;

    @Override
    public void start() {
        running = true;
        System.out.println("MyLifecycle 启动...");
    }

    @Override
    public void stop() {
        running = false;
        System.out.println("MyLifecycle 停止...");
    }

    @Override
    public boolean isRunning() {
        return running;
    }
}
