package org.janson.think.in.spring.dependency.injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 基于 {@link org.springframework.beans.factory.Aware} 接口回调的依赖注入示例
 *
 * @Description: 基于 {@Link Aware}依赖注入示例
 * @Author: Janson
 * @Date: 2020/7/26 16:25
 **/
public class AwareInterfaceDependencyInjectionDemo implements BeanFactoryAware, ApplicationContextAware {

    private static BeanFactory beanFactory;

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册Configuration Config (配置类) ->Spring Bean
        context.register(AwareInterfaceDependencyInjectionDemo.class);

        // 启动Spring 应用上下文
        context.refresh();

        System.out.println(beanFactory == context.getBeanFactory());
        System.out.println(applicationContext == context);

        // 显示关闭应用上下文。
        context.close();

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.applicationContext = applicationContext;
    }

}
