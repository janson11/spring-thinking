package org.janson.think.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description: {@link org.springframework.stereotype.Component} 扫描示例
 * @Author: Janson
 * @Date: 2020/9/12 21:48
 **/
//指定Class-paths
//@ComponentScan(basePackages = " org.janson.think.in.spring.annotation")
//@ComponentScan(value = " org.janson.think.in.spring.annotation")
@MyComponentScan2(scanBasePackages = "org.janson.think.in.spring.annotation")
public class ComponentScanDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册Configuration Class
        context.register(ComponentScanDemo.class);

        // 启动Spring 应用上下文
        context.refresh();
        // 依赖查找TestClass Bean
        // TestClass 标注 @MyComponent2
        // @MyComponent2 <-@MyComponent <-@Component
        // 从Spring 4.0开始支持多层次@Component派生
        TestClass testClass = context.getBean(TestClass.class);
        System.out.println(testClass);
        // 关闭Spring 应用上下文
        context.close();

    }
}
