package org.janson.think.in.spring.questions;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 学生类
 * @Author: Janson
 * @Date: 2020/9/26 16:48
 **/
@Data
public class Student {
    private Long id;
    private String name;
    @Autowired
    private ClassRoom classRoom;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classRoom.name=" + classRoom.getName() +
                '}';
    }
}
