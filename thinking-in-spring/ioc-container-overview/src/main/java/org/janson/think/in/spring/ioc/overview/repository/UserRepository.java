package org.janson.think.in.spring.ioc.overview.repository;

import lombok.Getter;
import lombok.Setter;
import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;

import java.util.Collection;

/**
 * @Description: 用户信息仓库
 * @Author: Janson
 * @Date: 2020/7/19 17:44
 **/
@Getter
@Setter
public class UserRepository {

    /**
     * 自定义的bean
     */
    private Collection<User> users;

    /**
     * 内建非Bean对象（依赖）
     */
    private BeanFactory beanFactory;

    private ObjectFactory<ApplicationContext> objectFactory;

}
