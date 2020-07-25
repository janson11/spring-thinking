package org.janson.think.in.spring.ioc.overview.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description: 用户类
 * @Author: Janson
 * @Date: 2020/7/19 12:47
 **/
@Getter
@Setter
@ToString
public class User {
    private Integer id;
    private String name;

    public static User createUser() {
        User user = new User();
        user.setId(1);
        user.setName("janson");
        return user;
    }
}
