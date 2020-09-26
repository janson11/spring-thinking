package org.janson.think.in.spring.questions;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @Description: BeanFactory 循环引用（依赖）示例
 * @Author: Janson
 * @Date: 2020/9/26 16:46
 **/
public class CircularReferencesDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册Config class
        context.register(CircularReferencesDemo.class);
        // 如果设置为 false 则抛出异常：Requested bean is currently in creation: Is there an unresolvable circular reference?
//        context.setAllowCircularReferences(false);
        context.setAllowCircularReferences(true);
        context.refresh();
        System.out.println("Student:" + context.getBean(Student.class));
        System.out.println("ClassRoom:" + context.getBean(ClassRoom.class));
        context.close();

    }

    @Bean
    public static Student student() {
        Student student = new Student();
        student.setId(1L);
        student.setName("张三");
        return student;
    }

    @Bean
    public static ClassRoom classRoom() {
        ClassRoom classRoom = new ClassRoom();
        classRoom.setName("C1222");
        return classRoom;
    }
}
