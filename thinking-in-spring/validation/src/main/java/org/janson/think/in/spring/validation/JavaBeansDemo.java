package org.janson.think.in.spring.validation;

import org.janson.think.in.spring.ioc.overview.domain.User;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.stream.Stream;

/**
 * @Description: JavaBeans 示例
 * @Author: Janson
 * @Date: 2020/9/5 22:21
 **/
public class JavaBeansDemo {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);

        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {
                    System.out.println(propertyDescriptor);
                });
        System.out.println("------------------");
        Stream.of(beanInfo.getMethodDescriptors()).forEach(System.out::println);
    }
}
