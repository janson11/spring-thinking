package org.janson.think.in.spring.questions;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @Description: Bean缓存示例
 * @Author: Janson
 * @Date: 2020/9/26 10:14
 * @see ObjectFactory
 * @see ObjectProvider
 **/
public class BeanCachingDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册Config class
        context.register(BeanCachingDemo.class);
        context.refresh();
        BeanCachingDemo beanCachingDemo = context.getBean(BeanCachingDemo.class);
        for (int i = 0; i < 9; i++) {
            // Singleton Scope Bean是存在缓存
            System.out.println(beanCachingDemo == context.getBean(BeanCachingDemo.class));
        }

        User user = context.getBean(User.class);
        for (int i = 0; i < 9; i++) {
            // Singleton Scope Bean是存在缓存
            System.out.println(user == context.getBean(User.class));
        }
        context.close();
    }

    @Bean
    @Scope("prototype") // 原型Scope
    public static User user() {
        User user = new User();
        user.setId(1L);
        user.setName("小单");
        return user;
    }
}
