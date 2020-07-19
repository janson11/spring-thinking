package com.janson.ioc.java.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Setter/Getter方法
 * 可写方法（Writeable）/可度方法（Readable）
 * @Description: 描述人的POJO
 * @Author: Janson
 * @Date: 2020/7/19 10:25
 **/

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonNew {

    /**
     * String to String
     * 姓名 Property
     */
    private String name;

    /**
     * String to Integer
     * 年龄 Property
     */
    private String age;

}
