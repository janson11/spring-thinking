package org.janson.think.in.spring.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @Description: {@link Profile}示例
 * @Author: Janson
 * @Date: 2020/9/13 14:47
 **/
@Configuration
public class ProfileDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册Configuration Class
        context.register(ProfileDemo.class);
        // 获取Environment 对象（可配置的）
        ConfigurableEnvironment environment = context.getEnvironment();
        // 默认的profiles
        environment.setDefaultProfiles("odd");
        // 增加活跃的profiles
        environment.setActiveProfiles("even");

        // 启动Spring 应用上下文
        context.refresh();

        Integer number = context.getBean("number", Integer.class);
        System.out.println(number);
        // 关闭Spring 应用上下文
        context.close();
    }

    @Bean(name = "number")
    @Profile("odd") //奇数
    public Integer odd() {
        return 1;
    }

    @Bean(name = "number")
//    @Profile("even") //偶数
    @Conditional(EvenProfileCondition.class)
    public Integer even() {
        return 2;
    }


}
