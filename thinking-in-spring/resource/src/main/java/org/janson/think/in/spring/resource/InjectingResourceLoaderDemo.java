package org.janson.think.in.spring.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javax.annotation.PostConstruct;

/**
 * @Description: 注入 {@link ResourceLoader} 对象示例
 * @Author: Janson
 * @Date: 2020/8/30 17:05
 * @see Value
 * @see ResourceLoader
 * @see AnnotationConfigApplicationContext
 **/
public class InjectingResourceLoaderDemo implements ResourceLoaderAware {

    private ResourceLoader resourceLoader;

    @Autowired
    private ResourceLoader autowiredResourceLoader;

    @Autowired
    private AbstractApplicationContext applicationContext;


    @PostConstruct
    public void init() {
        System.out.println("resourceLoader == autowiredResourceLoader :" + (resourceLoader == autowiredResourceLoader));
        System.out.println("resourceLoader == applicationContext :" + (resourceLoader == applicationContext));
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类作为Configuration Class
        context.register(InjectingResourceLoaderDemo.class);
        context.refresh();
        context.close();
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
