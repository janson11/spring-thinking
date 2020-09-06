package org.janson.think.in.spring.conversion;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Description: Spring 自定义{@link java.beans.PropertyEditor}示例
 * @Author: Janson
 * @Date: 2020/9/6 11:31
 **/
public class SpringCustomizedPropertyEditorDemo {

    public static void main(String[] args) {
        // 创建 BeanFactory 并且启动容器
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/property-editors-context.xml");
        User user = applicationContext.getBean("user", User.class);

        applicationContext.refresh();
        System.out.println(user);
        applicationContext.close();

    }
}
