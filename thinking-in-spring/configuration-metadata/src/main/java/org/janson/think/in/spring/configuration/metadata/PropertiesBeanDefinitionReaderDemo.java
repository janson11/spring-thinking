package org.janson.think.in.spring.configuration.metadata;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionReader;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;

/**
 * @Description: {@link org.springframework.beans.factory.support.PropertiesBeanDefinitionReader} 示例
 * @Author: Janson
 * @Date: 2020/8/16 10:58
 **/
public class PropertiesBeanDefinitionReaderDemo {
    public static void main(String[] args) {
        // 创建IoC底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 创建面向Properties  资源的BeanDefinitionReader示例
        PropertiesBeanDefinitionReader beanDefinitionReader = new PropertiesBeanDefinitionReader(beanFactory);
        // Properties 资源加载默认通过ISO-8859-1 实际存储UTF-8
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:/META-INF/users-bean-definition.properties");
        EncodedResource encodedResource  = new EncodedResource(resource,"UTF-8");

        int beanDefinitionCounts = beanDefinitionReader.loadBeanDefinitions(encodedResource);
        System.out.println(String.format("已加载%d 个 BeanDefinition\n", beanDefinitionCounts));
        // 通过依赖查找获取User Bean
        User user = beanFactory.getBean(User.class);
        System.out.println(user);
    }
}
