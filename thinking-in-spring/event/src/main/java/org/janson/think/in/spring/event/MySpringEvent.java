package org.janson.think.in.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @Description: 自定义Spring事件
 * @Author: Janson
 * @Date: 2020/9/9 20:43
 **/
public class MySpringEvent extends ApplicationEvent {

    /**
     * 构造器
     *
     * @param message 事件消息
     */
    public MySpringEvent(String message) {
        super(message);
    }

    @Override
    public String getSource() {
        return (String) super.getSource();
    }

    public String getMessage() {
        return getSource();
    }
}
