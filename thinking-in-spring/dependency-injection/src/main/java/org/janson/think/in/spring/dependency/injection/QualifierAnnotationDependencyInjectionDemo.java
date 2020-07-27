package org.janson.think.in.spring.dependency.injection;

import org.janson.think.in.spring.dependency.injection.annotation.UserGroup;
import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;

/**
 * @Description: {@link org.springframework.beans.factory.annotation.Qualifier}注解依赖注入
 * @Author: Janson
 * @Date: 2020/7/27 20:11
 **/
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // superUser ->primary=true

    @Autowired
    @Qualifier("user") //指定Bean名称或者ID
    private User namedUser;

    @Autowired
    private Collection<User> allUsers; // 2个bean =user+superUser

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers; // 2个bean = user1+user2 ->user1+user2 +user3+user4


    @Autowired
    @UserGroup
    private Collection<User> groupedUsers; // 2个bean = user1+user2

    @Bean
    @Qualifier // 进行逻辑分组
    public User user1() {
        return createUser(7L);
    }

    @Bean
    @Qualifier // 进行逻辑分组
    public User user2() {
        return createUser(8L);
    }

    @Bean
    @UserGroup
    public User user3() {
        return createUser(9L);
    }

    @Bean
    @UserGroup
    public User user4() {
        return createUser(10L);
    }

    private static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Config (配置类) -> Spring Bean
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载XML 资源解析并且生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动Spring 应用上下文
        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo = applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);

        // 期待输出SuperUser
        System.out.println("demo.user= " + demo.user);
        // 期待输出user
        System.out.println("demo.namedUser= " + demo.namedUser);
        // 期待输出allUsers
        System.out.println("demo.allUsers= " + demo.allUsers);
        // 期待输出qualifierUsers
        System.out.println("demo.qualifierUsers= " + demo.qualifierUsers);
        // 期待输出groupedUsers
        System.out.println("demo.qualifierUsers= " + demo.groupedUsers);
        // 显示关闭应用上下文。
        applicationContext.close();

    }
}
