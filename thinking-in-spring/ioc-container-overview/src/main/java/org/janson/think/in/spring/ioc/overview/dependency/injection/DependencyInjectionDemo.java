package org.janson.think.in.spring.ioc.overview.dependency.injection;

import org.janson.think.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @Description: 依赖注入示例
 * @Author: Janson
 * @Date: 2020/7/19 12:42
 **/
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        // 配置XM配置文件
        // 启动应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:META-INF/dependency-injection-context.xml");
        // 自定义的bean
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository", UserRepository.class);
//        System.out.println(userRepository.getUsers());
        // 依赖注入（内建依赖）
        System.out.println(userRepository.getBeanFactory());
//        System.out.println(userRepository.getBeanFactory() == beanFactory);
        // 依赖查找
//        System.out.println(beanFactory.getBean(BeanFactory.class));
        //  容器内建 Bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("获取Environment类型的Bean:" + environment);
        ObjectFactory<ApplicationContext> userObjectFactory = userRepository.getObjectFactory();
        System.out.println(userObjectFactory.getObject() == beanFactory);
        whoIsIoCContainer(userRepository, beanFactory);
    }

    /**
     * 谁是真正的IoC容器
     *
     * @param userRepository
     * @param beanFactory
     */
    private static void whoIsIoCContainer(UserRepository userRepository, BeanFactory beanFactory) {
        System.out.println(userRepository.getBeanFactory() == beanFactory);
    }


}
