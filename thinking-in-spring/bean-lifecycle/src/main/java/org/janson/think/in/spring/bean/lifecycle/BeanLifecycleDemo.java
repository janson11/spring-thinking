package org.janson.think.in.spring.bean.lifecycle;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2020/8/13 23:34
 **/
public class BeanLifecycleDemo {

    public static void main(String[] args) throws InterruptedException {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 方法一：添加BeanPostProcessor实现 MyInstantiationAwareBeanPostProcessor
        // 方法二：将MyInstantiationAwareBeanPostProcessor作为Bean注册
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        // 添加MyDestructionAwareBeanPostProcessor 执行销毁前回调
        beanFactory.addBeanPostProcessor(new MyDestructionAwareBeanPostProcessor());
        // 添加CommonAnnotationBeanPostProcessor解决@PostConstruct
        beanFactory.addBeanPostProcessor(new CommonAnnotationBeanPostProcessor());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        // 加载Properties资源
        // 指定字符编码UTF-8
        String[] locations = {"META-INF/dependency-lookup-context.xml", "META-INF/bean-constructor-dependency-injection.xml"};
        int beanNumbers = reader.loadBeanDefinitions(locations);
        System.out.println("已加载 BeanDefinition数量：" + beanNumbers);

        // 显示执行 preInstantiateSingletons
        // SmartInitializingSingleton 通常在Spring ApplicationContext场景使用
        // preInstantiateSingletons将已经注册的BeanDefinition初始化成Spring Bean
        beanFactory.preInstantiateSingletons();
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);
        // 构造器注入按照类型注入，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);

        // 执行Bean销毁 (容器内)
        beanFactory.destroyBean("userHolder", userHolder);
        System.out.println(userHolder);
        // 执行BeanFactory中的单例bean
        beanFactory.destroySingletons();
        // 强制GC
        System.gc();
        // 等待一段时间
        Thread.sleep(1000);
        System.gc();
    }

}
