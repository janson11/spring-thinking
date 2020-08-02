package org.janson.think.in.spring.dependency.source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * @Description: ResolvableDependency作为依赖来源
 * @Author: Janson
 * @Date: 2020/8/2 12:27
 **/
public class ResolvableDependencyDemo {

    @Autowired
    private String value;

    @PostConstruct
    public void init() {
        System.out.println(value);
    }

    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();


        // 注册Configuration Config (配置类) -> Spring Bean
        applicationContext.register(ResolvableDependencyDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory ->{
            beanFactory.registerResolvableDependency(String.class, "Hello,World");
        } );
        // 启动Spring 应用上下文
        applicationContext.refresh();
    /*    AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        if (beanFactory instanceof ConfigurableListableBeanFactory) {
            ConfigurableListableBeanFactory configurableListableBeanFactory = ConfigurableListableBeanFactory.class.cast(beanFactory);
            configurableListableBeanFactory.registerResolvableDependency(String.class, "Hello,World");
        }*/


        applicationContext.close();
    }


}
