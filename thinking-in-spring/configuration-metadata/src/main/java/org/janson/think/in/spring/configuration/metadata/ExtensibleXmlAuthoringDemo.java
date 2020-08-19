package org.janson.think.in.spring.configuration.metadata;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @Description: Spring XML 元素扩展示例
 * @Author: Janson
 * @Date: 2020/8/20 0:00
 **/
public class ExtensibleXmlAuthoringDemo {

    public static void main(String[] args) {
        // 创建IoC底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 创建XML 资源的BeanDefinitionBuilder
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("META-INF/users-context.xml");
        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }
}
