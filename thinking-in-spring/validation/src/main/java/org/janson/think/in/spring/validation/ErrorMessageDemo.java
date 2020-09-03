package org.janson.think.in.spring.validation;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.context.MessageSource;
import org.springframework.context.support.StaticMessageSource;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.Locale;

/**
 * @Description: 错误文案示例
 * @Author: Janson
 * @Date: 2020/9/3 20:27
 **/
public class ErrorMessageDemo {
    public static void main(String[] args) {
        // 0 创建user对象
        User user = new User();
        user.setName("小单");
        // 1 选择errors ->BeanPropertyBindingResult
        Errors errors = new BeanPropertyBindingResult(user, "user");
        // 2 调用reject生成rejectValue
        // reject生成ObjectError、FieldError
        errors.reject("user.properties not null");
        errors.rejectValue("name", "name.required");
        // 3 获取Errors中ObjectError和FieldError
        List<ObjectError> globalErrors = errors.getGlobalErrors();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        List<ObjectError> allErrors = errors.getAllErrors();

        // 4 通过ObjectError和FiledError中的code和args来关联MessageSource实现
        MessageSource messageSource = createMessageSource();

        for (ObjectError error : allErrors) {
            String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }

    static MessageSource createMessageSource() {
        StaticMessageSource messageSource = new StaticMessageSource();
        messageSource.addMessage("user.properties not null", Locale.getDefault(), "User 属性不为空");
        messageSource.addMessage("name.required", Locale.getDefault(), "the name of User must not be null");
        messageSource.addMessage("id.required", Locale.getDefault(), "the id of User must not be null");
        return messageSource;
    }
}
