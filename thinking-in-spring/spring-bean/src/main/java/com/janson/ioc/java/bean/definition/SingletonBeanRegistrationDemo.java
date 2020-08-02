package com.janson.ioc.java.bean.definition;

import com.janson.ioc.java.bean.factory.DefaultUserFactory;
import com.janson.ioc.java.bean.factory.UserFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description: 单体Bean注册实例
 * @Author: Janson
 * @Date: 2020/8/2 11:33
 **/
public class SingletonBeanRegistrationDemo {

    public static void main(String[] args) {
        // 创建BeanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //创建一个外部UserFactory对象
        UserFactory userFactory = new DefaultUserFactory();
        SingletonBeanRegistry singletonBeanRegistry = applicationContext.getBeanFactory();
        // 注册外部单例对象
        singletonBeanRegistry.registerSingleton("userFactory", userFactory);
        // 启动Spring 应用上下文
        applicationContext.refresh();

        // 通过依赖查找的方式来获取UserFactory
        UserFactory userFactoryByLookup = applicationContext.getBean("userFactory", UserFactory.class);

        System.out.println("userFactory==userFactoryByLookup：" + (userFactory == userFactoryByLookup));

        // 关闭应用上下文
        applicationContext.close();
    }
}
