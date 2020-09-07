package org.janson.think.in.spring.generic;

import org.springframework.core.GenericCollectionTypeResolver;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: {@link GenericCollectionTypeResolver}示例
 * @Author: Janson
 * @Date: 2020/9/7 20:48
 **/
public class GenericCollectionTypeResolverDemo {

    private static StringList stringList;
    private static List<String> strings;

    public static void main(String[] args) throws NoSuchFieldException {
        // getCollectionType返回具体化泛型参数类型集合的成员类型=String
        System.out.println(GenericCollectionTypeResolver.getCollectionType(StringList.class));
        System.out.println(GenericCollectionTypeResolver.getCollectionType(ArrayList.class));
        Field field = GenericCollectionTypeResolverDemo.class.getDeclaredField("stringList");
        Field field1 = GenericCollectionTypeResolverDemo.class.getDeclaredField("strings");
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field));
        System.out.println(GenericCollectionTypeResolver.getCollectionFieldType(field1));
    }
}
