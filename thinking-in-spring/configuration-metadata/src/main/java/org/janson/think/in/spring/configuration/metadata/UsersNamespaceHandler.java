package org.janson.think.in.spring.configuration.metadata;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @Description: "users.xsd" {@link org.springframework.beans.factory.xml.NamespaceHandler}
 * @Author: Janson
 * @Date: 2020/8/19 23:34
 **/
public class UsersNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        // 将 "user"元素注册对应的BeanDefinitionParser实现
        registerBeanDefinitionParser("user", new UserBeanDefinitionParser());

    }
}
