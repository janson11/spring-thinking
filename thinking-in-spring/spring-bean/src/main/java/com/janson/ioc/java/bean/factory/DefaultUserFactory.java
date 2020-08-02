package com.janson.ioc.java.bean.factory;

import org.janson.think.in.spring.ioc.overview.domain.User;

/**
 * @Description: 用户默认工厂实现类
 * @Author: Janson
 * @Date: 2020/8/2 11:41
 **/
public class DefaultUserFactory implements UserFactory {
    @Override
    public User createUser() {
        User user = new User();
        user.setId(107L);
        user.setName("测试用户");
        return user;
    }
}
