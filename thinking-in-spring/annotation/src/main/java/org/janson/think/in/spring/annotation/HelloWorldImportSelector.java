package org.janson.think.in.spring.annotation;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Description: HelloWorld 模块 {@link ImportSelector}实现
 * @Author: Janson
 * @Date: 2020/9/13 12:40
 **/
public class HelloWorldImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"org.janson.think.in.spring.annotation.HelloWorldConfiguration"};// 导入
    }
}
