package org.janson.think.in.spring.bean.lifecycle;

import org.janson.think.in.spring.ioc.overview.domain.User;

/**
 * @Description: User Holder 类
 * @Author: Janson
 * @Date: 2020/8/10 23:29
 **/
public class UserHolder {
    private final User user;

    public UserHolder(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" +
                "user=" + user +
                '}';
    }
}
