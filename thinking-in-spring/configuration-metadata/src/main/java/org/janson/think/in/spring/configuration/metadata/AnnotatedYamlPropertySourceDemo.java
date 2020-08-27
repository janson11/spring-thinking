package org.janson.think.in.spring.configuration.metadata;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.janson.think.in.spring.ioc.overview.enums.City;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

/**
 * @Description: 基于注解的YAML 外部化配置示例
 * @Author: Janson
 * @Date: 2020/8/27 20:35
 **/
@PropertySource(name = "yamlPropertySource", value = "classpath:/META-INF/user.yaml", factory = YamlPropertySourceFactory.class)
public class AnnotatedYamlPropertySourceDemo {

    /**
     * user.name 是Java Properties默认存在，当前用户:janso 而非配置文件中定义的"小单"
     *
     * @return
     **/
    @Bean
    public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name, @Value("${user.city}") City city) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setCity(city);
        return user;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册当前类作为Configuration class
        context.register(AnnotatedYamlPropertySourceDemo.class);
        // 启动应用上下文
        context.refresh();
        User user = context.getBean("user", User.class);
        System.out.println(user);
        // 关闭应用上下文
        context.close();
    }
}
