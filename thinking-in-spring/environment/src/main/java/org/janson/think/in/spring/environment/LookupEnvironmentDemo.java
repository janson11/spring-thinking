package org.janson.think.in.spring.environment;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @Description: 依赖注入  {@link Environment}示例
 * @Author: Janson
 * @Date: 2020/9/16 20:57
 **/
public class LookupEnvironmentDemo implements EnvironmentAware {

    private Environment environment;


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册Configuration Class
        context.register(LookupEnvironmentDemo.class);

        // 启动Spring 应用上下文
        context.refresh();

        LookupEnvironmentDemo lookupEnvironmentDemo = context.getBean(LookupEnvironmentDemo.class);

        // 通过 Environment Bean依赖查找
        Environment environment = context.getBean(ConfigurableApplicationContext.ENVIRONMENT_BEAN_NAME, Environment.class);

        System.out.println(lookupEnvironmentDemo.environment);
        System.out.println(lookupEnvironmentDemo.environment == environment);

        // 关闭Spring 应用上下文
        context.close();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
