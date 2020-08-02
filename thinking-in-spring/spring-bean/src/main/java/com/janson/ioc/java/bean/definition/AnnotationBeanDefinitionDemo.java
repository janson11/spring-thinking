package com.janson.ioc.java.bean.definition;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * @Description: 注解BeanDefinition的示例
 * @Author: Janson
 * @Date: 2020/7/23 23:20
 **/
// 通过@Import来进行导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Config (配置类)
        applicationContext.register(AnnotationBeanDefinitionDemo.class);

        // 通过BeanDefinition注册API实现
        // 1命名方式注册bean
        registerUserBeanDefinition(applicationContext,"janson-han-user");
        // 2非命名方式注册bean
        registerUserBeanDefinition(applicationContext);

        // 启动Spring 应用上下文
        applicationContext.refresh();

        // 1 通过@Bean方式定义
        System.out.println("Config 类型的所有Beans" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User 类型的所有Beans" + applicationContext.getBeansOfType(User.class));


        // 显示关闭应用上下文。
        applicationContext.close();


    }

    /**
     * 命名Bean的注册方式
     *
     * @param registry
     * @param beanName
     */
    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("id", 1)
                .addPropertyValue("name", "janson");

        // 如果是beanName,参数存在
        if (StringUtils.hasText(beanName)) {
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }


    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }


    // 2 定义@Component 当前类作为Spring Bean （组件）
    @Component
    static class Config {
        @Bean(name = {"user", "janson-user"})
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("janson");
            return user;
        }

    }

}
