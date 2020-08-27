package org.janson.think.in.spring.configuration.metadata;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 外部化配置示例
 * @Author: Janson
 * @Date: 2020/8/27 20:11
 **/
@PropertySource("classpath:/META-INF/users-bean-definition.properties")
public class PropertySourceDemo {

    /**
     * user.name 是Java Properties默认存在，当前用户:janso 而非配置文件中定义的"小单"
     *
     * @return
     **/
    @Bean
    public User configuredUser(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        return user;
    }


    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 扩展Environment中的PropertiesSources
        // 添加PropertySource操作必须在refresh方法之前完成。
        Map<String, Object> map = new HashMap<>();
        map.put("user.name", "小单");
        org.springframework.core.env.PropertySource propertySource = new MapPropertySource("first-property-source", map);
        context.getEnvironment().getPropertySources().addFirst(propertySource);
        //注册当前类作为Configuration class
        context.register(PropertySourceDemo.class);
        // 启动应用上下文
        context.refresh();
        Map<String, User> userMap = context.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            System.out.printf("User Bean name:%s,context:%s\n", entry.getKey(), entry.getValue());
        }
        System.out.println(context.getEnvironment().getPropertySources());
        // 关闭应用上下文
        context.close();
    }

}
