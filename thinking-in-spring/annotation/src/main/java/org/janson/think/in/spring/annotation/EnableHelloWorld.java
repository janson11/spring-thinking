package org.janson.think.in.spring.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 激活"HelloWorld"模块注解
 * @Author: Janson
 * @Date: 2020/9/13 11:56
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Import(HelloWorldConfiguration.class) // 第二步 通过@Import导入具体实现
//@Import(HelloWorldImportSelector.class)
@Import(HelloWorldBeanDefinitionRegistrar.class)
public @interface EnableHelloWorld { // 第一步 通过@EnableXXX命名
    // 方法一：通过Configuration Class实现
    // 方法二：通过ImportSelector接口实现
    // 方法三：通过ImportBeanDefinitionRegistrar接口实现

}
