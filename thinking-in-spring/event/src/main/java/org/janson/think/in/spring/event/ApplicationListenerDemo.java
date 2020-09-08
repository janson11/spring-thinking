package org.janson.think.in.spring.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.GenericApplicationContext;

/**
 * @Description: {@link org.springframework.context.ApplicationListener} 示例
 * @Author: Janson
 * @Date: 2020/9/8 19:55
 **/
public class ApplicationListenerDemo {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent event) {
                System.out.println("接收到Spring事件：" + event);
            }
        });

        // 启动 Spring应用上下文
        context.refresh();
        // 启动 Spring上下文
        context.start();
        // 关闭 Spring应用上下文
        context.close();

    }

}
