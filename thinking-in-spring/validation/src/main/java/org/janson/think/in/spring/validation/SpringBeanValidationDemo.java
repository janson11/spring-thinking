package org.janson.think.in.spring.validation;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @Description: Spring Bean Validation 整合示例
 * @Author: Janson
 * @Date: 2020/9/3 23:30
 * @see Validator
 * @see org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
 **/
public class SpringBeanValidationDemo {

    public static void main(String[] args) {

        // 启动应用上下文
        ConfigurableApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/bean-validation-context.xml");

//        Validator validator = applicationContext.getBean(Validator.class);
//        System.out.println(validator instanceof LocalValidatorFactoryBean);

        UserProcessor userProcessor = applicationContext.getBean(UserProcessor.class);
        userProcessor.processUser(new User());
        // 关闭应用上下文
        applicationContext.close();

    }


    @Component
    @Validated
    static class UserProcessor {

        public void processUser(@Valid User user) {
            System.out.println(user);
        }

    }


    @Validated
    static class User {
        @NotNull
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
