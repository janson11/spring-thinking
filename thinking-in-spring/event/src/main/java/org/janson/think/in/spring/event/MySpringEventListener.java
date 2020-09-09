package org.janson.think.in.spring.event;

import org.springframework.context.ApplicationListener;

/**
 * @Description: 自定义Spring事件监听 器
 * @Author: Janson
 * @Date: 2020/9/9 20:42
 **/
public class MySpringEventListener implements ApplicationListener<MySpringEvent> {
    @Override
    public void onApplicationEvent(MySpringEvent event) {
        System.out.printf("[线程：%s] 监听事件：%s\n", Thread.currentThread().getName(), event);
    }
}
