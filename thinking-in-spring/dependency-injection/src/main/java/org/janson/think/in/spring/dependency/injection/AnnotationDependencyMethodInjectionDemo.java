package org.janson.think.in.spring.dependency.injection;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * @Description: 基于方法依赖注入示例
 * @Author: Janson
 * @Date: 2020/7/26 16:25
 **/
public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder;

    private UserHolder userHolder2;


    @Autowired
    public void initUserHolder(UserHolder userHolder) {
        this.userHolder = userHolder;
    }


    @Resource
    public void initUserHolder2(UserHolder userHolder2) {
        this.userHolder2 = userHolder2;
    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }

    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();

        // 注册Configuration Config (配置类) -> Spring Bean
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);
        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载XML 资源解析并且生成BeanDefinition
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 启动Spring 应用上下文
        applicationContext.refresh();
        // 依赖查找并且创建Bean
        AnnotationDependencyMethodInjectionDemo demo = applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);
        UserHolder userHolder = demo.userHolder;
        UserHolder userHolder2 = demo.userHolder2;

        System.out.println(userHolder);
        System.out.println(userHolder2);
        System.out.println(userHolder == userHolder2);

        // 显示关闭应用上下文。
        applicationContext.close();

    }

}
