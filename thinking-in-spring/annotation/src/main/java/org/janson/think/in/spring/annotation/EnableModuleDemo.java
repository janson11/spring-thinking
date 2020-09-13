package org.janson.think.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description: Enable注解驱动
 * @Author: Janson
 * @Date: 2020/9/13 11:55
 **/
@EnableHelloWorld
public class EnableModuleDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册Configuration Class
        context.register(EnableModuleDemo.class);

        // 启动Spring 应用上下文
        context.refresh();
        // 依赖查找TestClass Bean
        // TestClass 标注 @MyComponent2
        // @MyComponent2 <-@MyComponent <-@Component
        // 从Spring 4.0开始支持多层次@Component派生
        String helloWorld = context.getBean("helloWorld", String.class);
        System.out.println(helloWorld);
        // 关闭Spring 应用上下文
        context.close();

    }
}
