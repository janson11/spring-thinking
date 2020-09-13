package org.janson.think.in.spring.annotation;

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
@MyComponentScan
public @interface MyComponentScan2 {

    @AliasFor(annotation = MyComponentScan.class, attribute = "scanBasePackages") //隐形别名
            // 多态，子注解提供新的属性方法引用"父"（元）注解中的属性方法
            String[] scanBasePackages() default {};
    // @MyComponentScan2.basePackages
    // ->@MyComponentScan.scanBasePackages
    // -> @ComponentScan.basePackages
    // -> @ComponentScan.value


}
