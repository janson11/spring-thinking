package org.janson.think.in.spring.event;

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
        context.addApplicationListener(new MySpringEventListener());
        // 2、启动应用上下文
        context.refresh();
        // 3、发布自定义Spring事件
        context.publishEvent(new MySpringEvent("Hello,World"));
        // 4、关闭Spring应用上下文
        context.close();
    }
}
