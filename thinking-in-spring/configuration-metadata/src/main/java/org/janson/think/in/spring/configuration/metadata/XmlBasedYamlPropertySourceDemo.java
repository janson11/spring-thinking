package org.janson.think.in.spring.configuration.metadata;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @Description: 基于XML YAML 外部化配置示例
 * @Author: Janson
 * @Date: 2020/8/27 20:35
 **/
public class XmlBasedYamlPropertySourceDemo {

    public static void main(String[] args) {

        // 创建IoC底层容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 创建XML 资源的BeanDefinitionBuilder
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("META-INF/yaml-property-source-context.xml");
        Map<String, Object> map = beanFactory.getBean("yamlMap", Map.class);
        System.out.println(map);
    }
}
