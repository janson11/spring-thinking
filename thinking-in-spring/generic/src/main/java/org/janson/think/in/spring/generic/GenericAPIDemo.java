package org.janson.think.in.spring.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * @Description: Java 泛型API示例
 * @Author: Janson
 * @Date: 2020/9/7 19:38
 **/
public class GenericAPIDemo {
    public static void main(String[] args) {
        // 原生类型
        Class intClass = int.class;
        // 数组类型
        Class objectArrayClass = Object[].class;
        // 原始类型 String
        Class rawClass = String.class;
        // 泛型参数类型
        ParameterizedType parameterizedType = (ParameterizedType) ArrayList.class.getGenericSuperclass();
        System.out.println(parameterizedType.toString());

        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        Stream.of(typeArguments)
                .map(TypeVariable.class::cast)
                .forEach(System.out::println);

    }
}
