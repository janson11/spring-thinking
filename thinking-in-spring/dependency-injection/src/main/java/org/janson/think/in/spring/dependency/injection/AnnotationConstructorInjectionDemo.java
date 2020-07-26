package org.janson.think.in.spring.dependency.injection;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Description: 基于Constructo依赖注入示例
 * @Author: Janson
 * @Date: 2020/7/26 16:25
 **/
public class AnnotationConstructorInjectionDemo {

    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Config (配置类)
        applicationContext.register(AnnotationConstructorInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载XML 资源解析并且生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动Spring 应用上下文
        applicationContext.refresh();
        // 依赖查找并且创建Bean
        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        System.out.println(userHolder);

        // 显示关闭应用上下文。
        applicationContext.close();

    }


    @Bean
    public UserHolder userHolder(User user) {
        // setter 注入
/*        UserHolder userHolder = new UserHolder();
        userHolder.setUser(user);
        return userHolder;*/
        // 构造器注入
        return new UserHolder(user);
    }
}
