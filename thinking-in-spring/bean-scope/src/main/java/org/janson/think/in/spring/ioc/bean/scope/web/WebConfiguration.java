package org.janson.think.in.spring.ioc.bean.scope.web;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Description: Web MVC 配置类
 * @Author: Janson
 * @Date: 2020/8/8 18:28
 **/
@Configuration
@EnableWebMvc
public class WebConfiguration {

    @Bean
//    @RequestScope
    @SessionScope
    public User user() {
        User user = new User();
        user.setId(1L);
        user.setName("小单");
        return user;
    }
}
