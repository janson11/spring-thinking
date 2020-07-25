package org.janson.think.in.spring.dependency.lookup;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * {@Link ObjectProvider }
 *
 * @Description: 通过ObjectProvider进行依赖查找
 * @Author: Janson
 * @Date: 2020/7/24 20:44
 **/
public class ObjectProviderDemo {

    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Config (配置类)
        applicationContext.register(ObjectProviderDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        lookupByObjectProvider(applicationContext);

    }

    @Bean
    public String helloWorld() {// 方法名就是Bean的名称
        return "Hello World";
    }


    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getObject());
    }
}
