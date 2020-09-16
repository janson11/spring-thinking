package org.janson.think.in.spring.environment;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: {@link PropertyPlaceholderConfigurer} 处理属性占位符示例
 * @Author: Janson
 * @Date: 2020/9/15 20:49
 **/
public class PropertyPlaceholderConfigurerDemo {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/placeholders-resolver.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
        context.close();
    }
}
