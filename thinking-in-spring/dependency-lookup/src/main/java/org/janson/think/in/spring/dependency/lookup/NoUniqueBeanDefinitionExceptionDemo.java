package org.janson.think.in.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Description: NoUniqueBeanDefinitionException 示例
 * @Author: Janson
 * @Date: 2020/7/25 21:08
 **/
public class NoUniqueBeanDefinitionExceptionDemo {

    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Config (配置类)
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);

        // 启动应用上下文
        applicationContext.refresh();

        try {
            // 由于Spring应用上下文存在两个String类型的Bean,通过单一类型查找会抛出异常
            applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err.printf("当前Spring应用上下文存在%d个%s类型的Bean，具体类型：%s\n", e.getNumberOfBeansFound()
                    , String.class.getName(), e.getMessage());
        }
        applicationContext.close();
    }


    @Bean
    public String bean1() {
        return "bean1";
    }

    @Bean
    public String bean2() {
        return "bean2";
    }

    @Bean
    public String bean3() {
        return "bean3";
    }
}
