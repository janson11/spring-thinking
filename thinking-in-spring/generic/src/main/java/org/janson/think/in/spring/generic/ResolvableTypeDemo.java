package org.janson.think.in.spring.generic;

import org.springframework.core.ResolvableType;

/**
 * @Description: {@link org.springframework.core.ResolvableType} Demo
 * @Author: Janson
 * @Date: 2020/9/7 23:04
 **/
public class ResolvableTypeDemo {
    public static void main(String[] args) {
        // 工厂创建
        // StringList -> ArrayList ->AbstractList ->List ->Collection
        ResolvableType resolvableType = ResolvableType.forClass(StringList.class);
        // ArrayList
        resolvableType.getSuperType();
        // AbstractList
        resolvableType.getSuperType().getSuperType();
        // 获取Raw Type
        System.out.println(resolvableType.asCollection().resolve());
        // 获取泛型参数类型
        System.out.println(resolvableType.asCollection().resolveGeneric(0));

    }
}
