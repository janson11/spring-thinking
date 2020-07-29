package org.janson.think.in.spring.dependency.injection;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Lazy;

import java.util.Map;
import java.util.Optional;

/**
 * @Description: 注解驱动的依赖注入处理过程
 * @Author: Janson
 * @Date: 2020/7/27 20:11
 **/
public class AnnotationDependencyInjectionResolutionDemo {



    @Autowired // 依赖查找（处理） +延迟
    @Lazy
    private User lazyUser;

    @Autowired
    private User user;// 依赖查找（处理）
                                    //DependencyDescriptor ->
                                    // 必须(required=true)
                                    // 实时注入（eager=true）
                                    // 通过类型（User.class）
                                    //字段名称（"user"）
                                    // 是否首要（primary =true）

    @Autowired
    private Map<String, User> users; // user  superUser


    @Autowired
    private Optional<Map<String, User>> userOptionals; // user  superUser



    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Config (配置类) -> Spring Bean
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载XML 资源解析并且生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动Spring 应用上下文
        applicationContext.refresh();

        AnnotationDependencyInjectionResolutionDemo demo = applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);

        // 期待输出SuperUser
        System.out.println("demo.user= " + demo.user);
        // 期待输出SuperUser
        System.out.println("demo.users= " + demo.users);
        // 期待输出SuperUser
        System.out.println("demo.userOptionals= " + demo.userOptionals);

        applicationContext.close();

    }
}
