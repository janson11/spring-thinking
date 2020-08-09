package org.janson.think.in.spring.bean.scope;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @Description: 自定义Scope {@link ThreadLocalScope}
 * @Author: Janson
 * @Date: 2020/8/9 10:07
 **/
public class ThreadLocalScopeDemo {


    @Bean
    @Scope(ThreadLocalScope.SCOPE_NAME)
    public User user() {
        return createUser();
    }


    private static User createUser() {
        User user = new User();
        user.setId(System.nanoTime());
        return user;
    }


    public static void main(String[] args) {
        // 创建BeanFactory
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类AnnotationApplicationContextAsIoCContainerDemo作为配置类
        applicationContext.register(ThreadLocalScopeDemo.class);

        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            // 注册自定义Scope
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });


        // 启动上下文
        applicationContext.refresh();
        scopedBeansByLookup(applicationContext);

        // 关闭
        applicationContext.close();
    }

    private static void scopedBeansByLookup(AnnotationConfigApplicationContext applicationContext) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                // user 是共享Bean对象
                User user = applicationContext.getBean("user", User.class);
                System.out.printf("[Thread id :%d ] user= %s%n", Thread.currentThread().getId(), user);
            });

            // 启动线程
            thread.start();

            try {
                // 强制线程执行完成
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
