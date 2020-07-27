package org.janson.think.in.spring.ioc.overview.container;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @Description: 注解能力 {@link org.springframework.context.ApplicationContext}IoC容器示例
 * @Author: Janson
 * @Date: 2020/7/19 20:48
 **/
@Configuration
public class AnnotationApplicationContextAsIoCContainerDemo {

    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        //将当前类AnnotationApplicationContextAsIoCContainerDemo作为配置类
        beanFactory.register(AnnotationApplicationContextAsIoCContainerDemo.class);
        // 启动上下文
        beanFactory.refresh();

        // 加载配置
//        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
//        String location = "classpath:META-INF/dependency-lookup-context.xml";
//        int beanDefinitionsCount = reader.loadBeanDefinitions(location);
//        System.out.println("Bean定义加载的数量：" + beanDefinitionsCount);
        lookupCollectionByType(beanFactory);

        // 关闭
        beanFactory.close();
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("查找到所有的User集合对象：" + users);
        }
    }

    /**
     * 通过Java 注解的方式定义一个bean
     *
     * @return
     */
    @Bean
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("小花");
        return user;
    }
}
