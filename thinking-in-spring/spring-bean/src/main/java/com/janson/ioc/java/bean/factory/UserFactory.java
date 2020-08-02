package com.janson.ioc.java.bean.factory;

import org.janson.think.in.spring.ioc.overview.domain.User;

/**
 * @Description: 用户工厂
 * @Author: Janson
 * @Date: 2020/8/2 11:41
 **/
public interface UserFactory {

    User createUser();
}
