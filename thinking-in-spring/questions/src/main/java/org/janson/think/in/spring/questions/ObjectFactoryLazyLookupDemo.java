package org.janson.think.in.spring.questions;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

/**
 * @Description: {@link ObjectFactory}延迟依赖查找示例
 * @Author: Janson
 * @Date: 2020/9/26 10:14
 * @see ObjectFactory
 * @see ObjectProvider
 **/
public class ObjectFactoryLazyLookupDemo {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(ObjectFactoryLazyLookupDemo.class);
        context.refresh();
        ObjectFactoryLazyLookupDemo objectFactoryLazyLookupDemo = context.getBean(ObjectFactoryLazyLookupDemo.class);
        // 代理对象
        ObjectFactory<User> userObjectFactory = objectFactoryLazyLookupDemo.userObjectFactory;
        ObjectProvider<User> userObjectProvider = objectFactoryLazyLookupDemo.userObjectProvider;
        System.out.println("userObjectFactory == userObjectProvider: " + (userObjectFactory == userObjectProvider));
        System.out.println("userObjectFactory.getClass == userObjectProvider.getClass: " + (userObjectFactory.getClass() == userObjectProvider.getClass()));

        // 实际对象
        System.out.println("user = " + userObjectFactory.getObject());
        System.out.println("user = " + userObjectProvider.getObject());
        System.out.println("user = " + context.getBean(User.class));

        context.close();
    }

    @Autowired
    private ObjectFactory<User> userObjectFactory;

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    @Bean
    @Lazy
    public static User user() {
        User user = new User();
        user.setId(1L);
        user.setName("小单");
        return user;
    }
}
