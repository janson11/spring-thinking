package org.janson.think.in.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: MyComponent 派生注解
 * @Author: Janson
 * @Date: 2020/9/12 23:51
 **/

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent // 元注解 实现@Component "派生性"
public @interface MyComponent2 {
}
