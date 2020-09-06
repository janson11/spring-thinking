package org.janson.think.in.spring.conversion;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * @Description: 自定义 {@link PropertyEditorRegistrar}实现
 * @Author: Janson
 * @Date: 2020/9/6 11:05
 **/
public class CustomizedPropertyEditorRegistrar implements PropertyEditorRegistrar {
    @Override
    public void registerCustomEditors(PropertyEditorRegistry registry) {
        // 1 通用类型实现
        // 2 Java Bean属性类型转换
        registry.registerCustomEditor(User.class, "context", new StringToPropertiesPropertyEditor());
    }
}
