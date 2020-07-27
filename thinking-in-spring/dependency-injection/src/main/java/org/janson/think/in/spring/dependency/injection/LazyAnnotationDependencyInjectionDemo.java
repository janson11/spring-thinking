package org.janson.think.in.spring.dependency.injection;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

/**
 * @Description: {@link org.springframework.beans.factory.ObjectProvider} 实现延迟依赖注入
 * @Author: Janson
 * @Date: 2020/7/27 20:11
 **/
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user; // 实时注入

    @Autowired
    private ObjectProvider<User> userObjectProvider; // 延迟注入

    @Autowired
    private ObjectFactory<Set<User>> usersObjectFactory;


    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Config (配置类) -> Spring Bean
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载XML 资源解析并且生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动Spring 应用上下文
        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo demo = applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);

        // 期待输出SuperUser
        System.out.println("demo.user= " + demo.user);
        // 期待输出user
        System.out.println("demo.userObjectProvider= " + demo.userObjectProvider.getObject());
        System.out.println("===================");
        // 期待输出user
        System.out.println("demo.usersObjectFactory= " + demo.usersObjectFactory.getObject());

        demo.userObjectProvider.forEach(System.out::println);

        applicationContext.close();

    }
}
