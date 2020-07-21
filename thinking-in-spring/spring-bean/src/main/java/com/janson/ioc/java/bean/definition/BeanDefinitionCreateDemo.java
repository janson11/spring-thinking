package com.janson.ioc.java.bean.definition;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition}
 *
 * @Description: 构建示例
 * @Author: Janson
 * @Date: 2020/7/21 19:47
 **/
public class BeanDefinitionCreateDemo {

    public static void main(String[] args) {
        // 1、通过BeanDefinitionBuilder构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 2、通过属性设置
        beanDefinitionBuilder
                .addPropertyValue("id", 1)
                .addPropertyValue("name", "janson");
        // 3、获取BeanDefinition的实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // beanDefinitionb并非Bean的终态，可以自定义修改
        System.out.println("BeanDefinitionBuilder" + beanDefinition);

        // 通过AbstractBeanDefinition以及派生类
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        propertyValues
                .add("id", 1)
                .add("name", "janson");
        genericBeanDefinition.setPropertyValues(propertyValues);
        System.out.println("AbstractBeanDefinition以及派生类" + genericBeanDefinition.getOriginatingBeanDefinition());
    }
}
