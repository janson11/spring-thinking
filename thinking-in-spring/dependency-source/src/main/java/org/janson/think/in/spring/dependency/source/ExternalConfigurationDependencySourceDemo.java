package org.janson.think.in.spring.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

/**
 * @Description: 外部化配置作为依赖注入示例
 * @Author: Janson
 * @Date: 2020/8/2 12:56
 **/
@Configuration
@PropertySource(value = "META-INF/default.properties",encoding = "UTF-8")
public class ExternalConfigurationDependencySourceDemo {

    @Value("${user.id:-1}")
    private Long id;

    @Value("${usr.name}")
    private String  name;

    @Value("${user.resource:classpath://META-INF/default.properties}")
    private Resource resource;

    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();


        // 注册Configuration Config (配置类) -> Spring Bean
        applicationContext.register(ExternalConfigurationDependencySourceDemo.class);

        // 启动Spring 应用上下文
        applicationContext.refresh();

        // 依赖查找ExternalConfigurationDependencySourceDemo bean
        ExternalConfigurationDependencySourceDemo demo = applicationContext.getBean(ExternalConfigurationDependencySourceDemo.class);

        System.out.println("demo.id=" + demo.id);
        System.out.println("demo.name=" + demo.name);
        System.out.println("demo.resource=" + demo.resource);
        applicationContext.close();
    }
}
