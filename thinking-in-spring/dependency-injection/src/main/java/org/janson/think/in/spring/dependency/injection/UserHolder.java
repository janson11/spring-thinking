package org.janson.think.in.spring.dependency.injection;

import lombok.*;
import org.janson.think.in.spring.ioc.overview.domain.User;

/**
 * @Description: User 的Holdler对象
 * @Author: Janson
 * @Date: 2020/7/26 16:08
 **/
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserHolder {

    private User user;
}
