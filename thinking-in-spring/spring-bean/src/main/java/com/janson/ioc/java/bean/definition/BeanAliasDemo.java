package com.janson.ioc.java.bean.definition;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: bean别名示例
 * @Author: Janson
 * @Date: 2020/7/21 20:57
 **/
public class BeanAliasDemo {

    public static void main(String[] args) {

        // 配置XML配置文件
        // 启动应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/bean-definition-context.xml");

        // 通过别名jansonUser获取曾用名user的bean
        User user = beanFactory.getBean("user", User.class);
        User jansonUser = beanFactory.getBean("janson-user", User.class);
        System.out.println("jansonUser与user是否相等" + (user == jansonUser));

    }
}
