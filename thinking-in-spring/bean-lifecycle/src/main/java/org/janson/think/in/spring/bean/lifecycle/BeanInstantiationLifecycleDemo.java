package org.janson.think.in.spring.bean.lifecycle;

import org.janson.think.in.spring.ioc.overview.domain.SuperUser;
import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ObjectUtils;

/**
 * @Description: Bean实例化生命周期示例
 * @Author: Janson
 * @Date: 2020/8/10 22:51
 **/
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        executeBeanFactory();
        System.out.println("-------------------------------");
        executeApplicationContext();
    }

    public static void executeBeanFactory() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 方法一：添加BeanPostProcessor实现 MyInstantiationAwareBeanPostProcessor
        // 方法二：将MyInstantiationAwareBeanPostProcessor作为Bean注册
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        // 加载Properties资源
        // 指定字符编码UTF-8
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        int beanNumbers = reader.loadBeanDefinitions(locations);
        System.out.println("已加载 BeanDefinition数量：" + beanNumbers);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);
        // 构造器注入按照类型注入，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);


    }

    public static void executeApplicationContext() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext();
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        applicationContext.setConfigLocations(locations);

        // 启动应用上下文
        applicationContext.refresh();
        User user = applicationContext.getBean("user", User.class);
        System.out.println(user);

        User superUser = applicationContext.getBean("superUser", User.class);
        System.out.println(superUser);
        // 构造器注入按照类型注入，resolveDependency
        UserHolder userHolder = applicationContext.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);
        // 关闭应用上下文
        applicationContext.close();
    }
}
