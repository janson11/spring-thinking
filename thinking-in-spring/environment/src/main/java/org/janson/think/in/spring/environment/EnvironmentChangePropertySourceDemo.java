package org.janson.think.in.spring.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 配置属性源变更示例
 * @Author: Janson
 * @Date: 2020/9/18 23:42
 **/
public class EnvironmentChangePropertySourceDemo {
    // 不具备动态更新能力
    @Value("${user.name}")
    private String userName;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册Configuration Class
        context.register(EnvironmentChangePropertySourceDemo.class);
        // 在Spring应用上下文启动前，调整Environment中的PropertySource
        ConfigurableEnvironment environment = context.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();

        Map<String, Object> source = new HashMap<>();
        source.put("user.name", "小单");
        MapPropertySource propertySource = new MapPropertySource("first-property-source", source);
        propertySources.addFirst(propertySource);
        // 启动Spring 应用上下文
        context.refresh();
        source.put("user.name", "007");
        EnvironmentChangePropertySourceDemo environmentChangePropertySourceDemo = context.getBean(EnvironmentChangePropertySourceDemo.class);
        System.out.println(environmentChangePropertySourceDemo.userName);

        for (PropertySource<?> ps : propertySources) {
            System.out.printf("PropertySource(name=%s) 'user.name' 属性：%s\n", ps.getName(), ps.getProperty("user.name"));
        }
        // 关闭Spring 应用上下文
        context.close();
    }
}
