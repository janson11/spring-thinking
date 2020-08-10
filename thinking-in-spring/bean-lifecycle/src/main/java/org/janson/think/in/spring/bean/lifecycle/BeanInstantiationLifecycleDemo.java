package org.janson.think.in.spring.bean.lifecycle;

import org.janson.think.in.spring.ioc.overview.domain.SuperUser;
import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.ObjectUtils;

/**
 * @Description: Bean实例化生命周期示例
 * @Author: Janson
 * @Date: 2020/8/10 22:51
 **/
public class BeanInstantiationLifecycleDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 添加BeanPostProcessor实现
        beanFactory.addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        // 加载Properties资源
        // 指定字符编码UTF-8
        String[] locations = {"META-INF/dependency-lookup-context.xml","META-INF/bean-constructor-dependency-injection.xml"};
        int beanNumbers = reader.loadBeanDefinitions(locations);
        System.out.println("已加载 BeanDefinition数量：" + beanNumbers);
        User user = beanFactory.getBean("user", User.class);
        System.out.println(user);

        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(superUser);
        // 构造器注入按照类型注入，resolveDependency
        UserHolder userHolder = beanFactory.getBean("userHolder", UserHolder.class);
        System.out.println(userHolder);

    }

    static class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
        @Override
        public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
            if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass))
                // 把配置完成 superUser  Bean 覆盖
                return new SuperUser();
            return null;
        }
    }
}
