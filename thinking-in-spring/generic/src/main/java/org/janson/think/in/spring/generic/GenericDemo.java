package org.janson.think.in.spring.generic;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Description: Java泛型示例
 * @Author: Janson
 * @Date: 2020/9/7 19:19
 **/
public class GenericDemo {
    public static void main(String[] args) {
        // Java 7 Diamond 语法
        Collection<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        // 编译时错误
//        list.add(1);
        // 泛型擦写
        Collection temp = list;
        temp.add(1);
        System.out.println(list);
        System.out.println("--------------");
        System.out.println(temp);

    }
}
