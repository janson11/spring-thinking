package com.janson.ioc.java.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

/**
 * {@link java.beans.BeanInfo}示例
 *
 * @Description:
 * @Author: Janson
 * @Date: 2020/7/19 10:32
 **/
public class BeanInfoDemo {

    public static void main(String[] args) throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {
                    System.out.println("前"+propertyDescriptor);
                    // PropertyDescriptor允许添加属性编辑器 PropertyEditor
                    Class<?> propertyType = propertyDescriptor.getPropertyType();
                    String propertyName = propertyDescriptor.getName();
                    if ("age".equals(propertyName)) {
                        propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
                        propertyDescriptor.createPropertyEditor(new PersonNew("小明","123"));
                        System.out.println("后"+propertyDescriptor);
                    }
                });
    }

    static class StringToIntegerPropertyEditor extends PropertyEditorSupport {
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }
    }

}
