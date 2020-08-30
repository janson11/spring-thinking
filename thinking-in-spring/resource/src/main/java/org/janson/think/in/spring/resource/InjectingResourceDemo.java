package org.janson.think.in.spring.resource;

import org.janson.think.in.spring.resource.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import java.util.stream.Stream;

/**
 * @Description: 注入 {@link Resource} 对象示例
 * @Author: Janson
 * @Date: 2020/8/30 17:05
 * @see Value
 * @see Resource
 * @see AnnotationConfigApplicationContext
 **/
public class InjectingResourceDemo {

    @Value("classpath:/META-INF/default.properties")
    private Resource defaultPropertiesResource;

    @Value("${user.dir}")
    private String currentProjectRootPath;

    @Value("classpath*:/META-INF/*.properties")
    private Resource[] propertiesResources;

    @PostConstruct
    public void init() {
        System.out.println(ResourceUtils.getContext(defaultPropertiesResource));
        System.out.println(currentProjectRootPath);
        System.out.println("====================");
        Stream.of(propertiesResources).map(ResourceUtils::getContext).forEach(System.out::println);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册当前类作为Configuration Class
        context.register(InjectingResourceDemo.class);
        context.refresh();
        context.close();
    }

}
