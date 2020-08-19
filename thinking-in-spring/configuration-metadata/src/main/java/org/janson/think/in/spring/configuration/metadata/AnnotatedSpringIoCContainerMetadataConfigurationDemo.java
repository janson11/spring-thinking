package org.janson.think.in.spring.configuration.metadata;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.Map;

/**
 * @Description: 基于Java注解 Spring IoC示例
 * @Author: Janson
 * @Date: 2020/8/19 20:29
 **/
@ImportResource("classpath:/META-INF/dependency-lookup-context.xml")
@Import(User.class)
@PropertySource("classpath:/META-INF/users-bean-definition.properties") // java 8+Repeatable支持
@PropertySource("classpath:/META-INF/users-bean-definition.properties")
public class AnnotatedSpringIoCContainerMetadataConfigurationDemo {

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
        //注册当前类作为Configuration class
        context.register(AnnotatedSpringIoCContainerMetadataConfigurationDemo.class);
        // 启动应用上下文
        context.refresh();
        Map<String, User> userMap = context.getBeansOfType(User.class);
        for (Map.Entry<String, User> entry : userMap.entrySet()) {
            System.out.printf("User Bean name:%s,context:%s\n", entry.getKey(), entry.getValue());
        }
        // 关闭应用上下文
        context.close();
    }
}
