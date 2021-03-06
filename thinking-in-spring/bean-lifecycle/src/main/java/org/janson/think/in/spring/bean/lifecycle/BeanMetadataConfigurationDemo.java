package org.janson.think.in.spring.bean.lifecycle;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

/**
 * @Description: Bean元信息配置示例
 * @Author: Janson
 * @Date: 2020/8/9 15:13
 **/
public class BeanMetadataConfigurationDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 实例化基于Properties资源 BeanDefinitionReader
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(beanFactory);
        // 加载Properties资源
        // 指定字符编码UTF-8
        String location = "META-INF/user.properties";
        Resource resource = new ClassPathResource(location);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        int beanNumbers = reader.loadBeanDefinitions(encodedResource);
        System.out.println("已加载 BeanDefinition数量：" + beanNumbers);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

    }
}
