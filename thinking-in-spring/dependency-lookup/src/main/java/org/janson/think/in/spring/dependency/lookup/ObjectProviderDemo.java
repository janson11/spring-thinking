package org.janson.think.in.spring.dependency.lookup;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * {@Link ObjectProvider }
 *
 * @Description: 通过ObjectProvider进行依赖查找
 * @Author: Janson
 * @Date: 2020/7/24 20:44
 **/
public class ObjectProviderDemo {

    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Config (配置类)
        applicationContext.register(ObjectProviderDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);

        lookupIfAvailable(applicationContext);

        lookupByStreamOps(applicationContext);
        //关闭应用上下文
        applicationContext.close();

    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
    /*    Iterable<String> stringIterable = objectProvider;
        for (String s : stringIterable) {
            System.out.println(s);
        }*/
        objectProvider.stream().forEach(System.out::println);
    }


    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable(User::createUser);
        System.out.println("当前User对象：" + user);
    }


    @Bean
    @Primary
    public String helloWorld() {// 方法名就是Bean的名称
        return "Hello World";
    }


    @Bean
    public String message() {
        return "Message";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }
}
