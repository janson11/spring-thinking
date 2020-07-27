package org.janson.think.in.spring.ioc.overview.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.janson.think.in.spring.ioc.overview.enums.City;
import org.springframework.core.io.Resource;

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
    private City city;
    private Resource configFileLocation;

    public static User createUser() {
        User user = new User();
        user.setId(1);
        user.setName("janson");
        return user;
    }
}
