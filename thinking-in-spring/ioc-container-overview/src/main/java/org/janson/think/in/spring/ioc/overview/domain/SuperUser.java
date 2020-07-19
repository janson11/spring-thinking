package org.janson.think.in.spring.ioc.overview.domain;

import lombok.Getter;
import lombok.Setter;
import org.janson.think.in.spring.ioc.overview.annotation.Super;

/**
 * @Description: 超级用户
 * @Author: Janson
 * @Date: 2020/7/19 17:06
 **/
@Getter
@Setter
@Super
public class SuperUser extends User {
    private String address;

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
