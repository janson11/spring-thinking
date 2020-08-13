package org.janson.think.in.spring.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * @Description: DestructionAwareBeanPostProcessor 实现
 * @Author: Janson
 * @Date: 2020/8/13 23:31
 **/
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {
    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            // afterSingletonsInstantiated() =The user holder V8
            userHolder.setDescription("The user holder V9");
            System.out.println("postProcessBeforeDestruction()=" + userHolder.getDescription());
        }
    }
}
