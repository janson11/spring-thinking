package org.janson.think.in.spring.dependency.lookup;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * {@link org.springframework.beans.factory.BeanCreationException}
 *
 * @Description: BeanCreationException 创建异常
 * @Author: Janson
 * @Date: 2020/7/25 21:21
 **/
public class BeanCreationExceptionDemo {
    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);
        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

        // 启动应用上下文
        applicationContext.refresh();

        applicationContext.close();
    }

    static class POJO implements InitializingBean {

        // CommonAnnotationBeanPostProcessor
        @PostConstruct
        public void init() throws Throwable {
            throw new Throwable("init(): For purpose...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("For purpose...");
        }
    }

}
