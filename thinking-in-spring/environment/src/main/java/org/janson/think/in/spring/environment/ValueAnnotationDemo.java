package org.janson.think.in.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2020/9/17 20:03
 **/
public class ValueAnnotationDemo {

    @Value("${user.name}")
    private String userName;

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册Configuration Class
        context.register(ValueAnnotationDemo.class);

        // 启动Spring 应用上下文
        context.refresh();

        ValueAnnotationDemo valueAnnotationDemo = context.getBean(ValueAnnotationDemo.class);

        System.out.println(valueAnnotationDemo.userName);

        // 关闭Spring 应用上下文
        context.close();
    }

}
