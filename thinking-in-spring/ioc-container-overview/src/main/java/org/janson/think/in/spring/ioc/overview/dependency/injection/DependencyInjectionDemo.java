package org.janson.think.in.spring.ioc.overview.dependency.injection;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.janson.think.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository", UserRepository.class);
//        System.out.println(userRepository.getUsers());
        // 依赖注入
        System.out.println(userRepository.getBeanFactory());
//        System.out.println(userRepository.getBeanFactory() == beanFactory);
        // 依赖查找
//        System.out.println(beanFactory.getBean(BeanFactory.class));
        ObjectFactory<ApplicationContext> userObjectFactory = userRepository.getObjectFactory();
        System.out.println(userObjectFactory.getObject()==beanFactory);

    }


}
