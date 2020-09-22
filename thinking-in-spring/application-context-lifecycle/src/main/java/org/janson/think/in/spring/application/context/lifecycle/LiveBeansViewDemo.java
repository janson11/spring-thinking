package org.janson.think.in.spring.application.context.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.LiveBeansView;

import java.io.IOException;

import static org.springframework.context.support.LiveBeansView.MBEAN_DOMAIN_PROPERTY_NAME;

/**
 * @Description: {@link LiveBeansView}示例
 * @Author: Janson
 * @Date: 2020/9/22 21:11
 **/
public class LiveBeansViewDemo {

    public static void main(String[] args) throws IOException {

        System.setProperty(MBEAN_DOMAIN_PROPERTY_NAME, "org.janson.think.in.spring");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(LiveBeansViewDemo.class);

        // 启动Spring应用上下文
        context.refresh();

        System.out.println("按任意键继续");

        // [ { "context": "org.springframework.context.annotation.AnnotationConfigApplicationContext@7c30a502", "parent": null, "beans": [ { "bean": "liveBeansViewDemo", "aliases": [], "scope": "singleton", "type": "org.janson.think.in.spring.application.context.lifecycle.LiveBeansViewDemo", "resource": "null", "dependencies": [] }] }]

        System.in.read();
        // 关闭Spring应用上下文
        context.close();
    }
}
