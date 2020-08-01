package org.janson.think.in.spring.dependency.injection;

import org.janson.think.in.spring.dependency.injection.annotation.InjectedUser;
import org.janson.think.in.spring.dependency.injection.annotation.MyAutowired;
import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

/**
 * @Description: 注解驱动的依赖注入处理过程
 * @Author: Janson
 * @Date: 2020/7/27 20:11
 **/
@Configuration
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


    @MyAutowired
    private Optional<Map<String, User>> userOptionals; // user  superUser

    @Inject
    private User injectedUser;

    @InjectedUser
    private User myInjectedUser;

/*    @Bean(name = AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        // @Autowired  新注解 +@InjectedUser
        Set<Class<? extends Annotation>> autowiredAnnotationType = new LinkedHashSet<>(Arrays.asList(Autowired.class, Inject.class, InjectedUser.class));
        beanPostProcessor.setAutowiredAnnotationTypes(autowiredAnnotationType);
        return beanPostProcessor;
    }*/


    @Bean()
    @Order(Ordered.LOWEST_PRECEDENCE - 3)
    public static AutowiredAnnotationBeanPostProcessor beanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor beanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        beanPostProcessor.setAutowiredAnnotationType(InjectedUser.class);
        return beanPostProcessor;
    }


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
        // 期待输出injectedUser
        System.out.println("demo.injectedUser= " + demo.injectedUser);
        // 期待输出SuperUser
        System.out.println("demo.users= " + demo.users);
        // 期待输出SuperUser
        System.out.println("demo.userOptionals= " + demo.userOptionals);
        // 期待输出myInjectedUser
        System.out.println("demo.myInjectedUser= " + demo.myInjectedUser);

        applicationContext.close();

    }
}
