package org.janson.think.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description: Spring 注解属性覆盖示例
 * @Author: Janson
 * @Date: 2020/9/13 11:22
 **/
@MyComponentScan2(packages = "org.janson.think.in.spring.annotation")
//@MyComponentScan2.scanBasePackage <-@MyComponentScan.scanBasePackage隐形覆盖
public class AttributeOverridesDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册Configuration Class
        context.register(AttributeOverridesDemo.class);

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
