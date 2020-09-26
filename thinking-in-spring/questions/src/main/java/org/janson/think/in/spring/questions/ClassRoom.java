package org.janson.think.in.spring.questions;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @Description: 教室类
 * @Author: Janson
 * @Date: 2020/9/26 16:48
 **/
@Data
public class ClassRoom {

    private String name;

    @Autowired
    private Collection<Student> students;
}
