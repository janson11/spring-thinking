package org.janson.think.in.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @Description: 层次性Spring事件传播实例
 * @Author: Janson
 * @Date: 2020/9/8 20:46
 **/
public class HierarchicalSpringEventPropagateDemo {
    public static void main(String[] args) {
        // 1、创建 parent Spring 应用上下文
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        parentContext.setId("parent-context");

        // 注册MyListener到parent Spring 应用上下文
        parentContext.register(MyListener.class);

        // 2、创建 current Spring 应用上下文
        AnnotationConfigApplicationContext currentContext = new AnnotationConfigApplicationContext();
        currentContext.setId("current-context");
        // 3、current->parent
        currentContext.setParent(parentContext);

        // 注册MyListener到current Spring 应用上下文
        currentContext.register(MyListener.class);
        // 4、启动parent Spring 应用上下文
        parentContext.refresh();

        // 5、启动parent Spring 应用上下文
        currentContext.refresh();

        // 6、关闭所有Spring 应用上下文
        currentContext.close();
        parentContext.close();

    }

    static class MyListener implements ApplicationListener<ApplicationContextEvent> {

        private static Set<ApplicationContextEvent> processEvents = new LinkedHashSet<>();

        @Override
        public void onApplicationEvent(ApplicationContextEvent event) {
            if (processEvents.add(event)) {
                System.out.printf("监听到Spring应用上下文[ID:%s] 事件：%s\n", event.getApplicationContext().getId(), event.getClass().getSimpleName());
            }
        }
    }

}
