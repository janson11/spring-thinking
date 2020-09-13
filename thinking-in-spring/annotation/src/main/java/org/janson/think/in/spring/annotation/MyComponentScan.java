package org.janson.think.in.spring.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 自定义 ComponentScan
 * @Author: Janson
 * @Date: 2020/9/13 10:34
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ComponentScan
public @interface MyComponentScan {

    @AliasFor(annotation = ComponentScan.class, attribute = "value") //隐形别名
            // 多态，子注解提供新的属性方法引用"父"（元）注解中的属性方法
            String[] scanBasePackages() default {"#"};
    // scanBasePackages -> @AliasFor @ComponentScan.value(显性别名)
    // @AliasFor @ComponentScan.value -> 传递隐形别名

}