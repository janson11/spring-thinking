package org.janson.think.in.spring.dependency.injection.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Description: 自定义依赖注入注解、
 * @Author: Janson
 * @Date: 2020/8/1 18:47
 **/
@Target({ METHOD, CONSTRUCTOR, FIELD })
@Retention(RUNTIME)
@Documented
public @interface InjectedUser {
}
