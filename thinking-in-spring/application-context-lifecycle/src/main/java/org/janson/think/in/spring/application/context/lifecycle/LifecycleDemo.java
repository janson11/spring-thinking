package org.janson.think.in.spring.application.context.lifecycle;

import org.springframework.context.Lifecycle;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.rootBeanDefinition;

/**
 * @Description: 自定义 {@link Lifecycle} 示例
 * @Author: Janson
 * @Date: 2020/9/22 23:36
 **/
public class LifecycleDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericWebApplicationContext();
        context.registerBeanDefinition("myLifecycle", rootBeanDefinition(MyLifecycle.class).getBeanDefinition());
        // 刷新Spring应用上下文
        context.refresh();
        // 启动Spring应用上下文
        context.start();
        // 停止Spring应用上下文
        context.stop();
        // 关闭Spring应用上下文
        context.close();
    }
}
