package org.janson.think.in.spring.generic;

import org.springframework.core.GenericTypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.core.GenericTypeResolver.resolveReturnType;
import static org.springframework.core.GenericTypeResolver.resolveReturnTypeArgument;

/**
 * @Description: {@link org.springframework.core.GenericTypeResolver} 示例
 * @Author: Janson
 * @Date: 2020/9/7 19:55
 **/
public class GenericTypeResolverDemo {
    public static void main(String[] args) throws NoSuchMethodException {
        // String是Comparable<String>具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, Comparable.class, "getString");
        // ArrayList<Object>是 List泛型参数类型的具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getList");
        // StringList是 List泛型参数类型的具体化
        displayReturnTypeGenericInfo(GenericTypeResolverDemo.class, List.class, "getStringList");
    }


    public StringList getStringList() {
        return null;
    }

    public static ArrayList<Object> getList() {
        return null;
    }

    public static String getString() {
        return null;
    }

    private static void displayReturnTypeGenericInfo(Class<?> containingClass, Class<?> genericIfc, String methodName, Class... argumentTypes) throws NoSuchMethodException {
        Method method = containingClass.getMethod(methodName, argumentTypes);
        Class<?> returnType = resolveReturnType(method, containingClass);
        // 常规类作为方法返回值
        System.out.printf("GenericTypeResolver.resolveReturnType(%s,%s)=%s\n", methodName, containingClass.getSimpleName(), returnType);
        // 常规类型不具备泛型参数类型List<E>
        Class<?> returnTypeArgument = resolveReturnTypeArgument(method, genericIfc);
        System.out.printf("GenericTypeResolver.resolveReturnTypeArgument(%s,%s)=%s\n", methodName, containingClass.getSimpleName(), returnTypeArgument);

        Map<TypeVariable, Type> typeVariableMap = GenericTypeResolver.getTypeVariableMap(StringList.class);
        System.out.println(typeVariableMap);
    }

    static class StringList extends ArrayList<String> {// 泛型参数具体化（字节码有记录）

    }
}
