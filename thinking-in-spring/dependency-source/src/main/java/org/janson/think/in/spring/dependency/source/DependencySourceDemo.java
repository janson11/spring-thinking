package org.janson.think.in.spring.dependency.source;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * @Description: 依赖来源示例
 * @Author: Janson
 * @Date: 2020/8/2 10:24
 **/
public class DependencySourceDemo {

    // 注入在postProcessProperties 方法执行，早于Setter注入，也早于@PostConstruct
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void initInjection() {
        System.out.println("beanFactory==applicationContext " + (beanFactory == applicationContext));
        System.out.println("beanFactory==applicationContext.getBeanFactory " + (beanFactory == applicationContext.getAutowireCapableBeanFactory()));
        System.out.println("resourceLoader==applicationContext " + (resourceLoader == applicationContext));
        System.out.println("beanFactory==applicationEventPublisher " + (applicationEventPublisher == applicationContext));
    }

    @PostConstruct
    public void initByLookup() {
        getBean(BeanFactory.class);
        getBean(ApplicationContext.class);
        getBean(ResourceLoader.class);
        getBean(ApplicationEventPublisher.class);
    }

    private <T> T getBean(Class<T> beanType) {
        try {
            return beanFactory.getBean(beanType);
        } catch (NoSuchBeanDefinitionException e) {
            System.err.println("当前类型" + beanType.getName() + " 无法在BeanFactory中查找！");
        }
        return null;
    }


    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Config (配置类) -> Spring Bean
        applicationContext.register(DependencySourceDemo.class);

        // 启动Spring 应用上下文
        applicationContext.refresh();

        applicationContext.close();

    }

}
