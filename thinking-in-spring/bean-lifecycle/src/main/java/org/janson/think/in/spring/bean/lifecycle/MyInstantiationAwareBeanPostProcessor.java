package org.janson.think.in.spring.bean.lifecycle;

import org.janson.think.in.spring.ioc.overview.domain.SuperUser;
import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.util.ObjectUtils;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2020/8/11 23:07
 **/
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("superUser", beanName) && SuperUser.class.equals(beanClass))
            // 把配置完成 superUser  Bean 覆盖
            return new SuperUser();
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("user", beanName) && User.class.equals(bean.getClass())) {
            // user 对象不允许属性赋值(填入) (配置元信息—>属性值)
            User user = (User) bean;
            user.setId(2l);
            user.setName("janson");
            return false;
        }
        return true;
    }

    // user 是跳过Bean属性赋值(填入)
    // superUsery也是完全跳过Bean实例化(Bean 属性值（填入）)
    // userHolder
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        // 对"userHolder" Bean进行拦截
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            // 假设<property name="number" value="1"></property>配置的话，那么在PropertyValues就包含一个PropertyValue。
            final MutablePropertyValues propertyValues;
            if (pvs instanceof MutablePropertyValues) {
                propertyValues = (MutablePropertyValues) pvs;
            } else {
                propertyValues = new MutablePropertyValues();
            }

            // 如果存在的话就覆盖
            if (propertyValues.contains("description")) {
                // propertyValue value是不可变的
//                    PropertyValue propertyValue = propertyValues.getPropertyValue("description");
                propertyValues.removePropertyValue("description");
                propertyValues.addPropertyValue("description", "The User Holder V2");
            }
            propertyValues.addPropertyValue("number", "1");
            return propertyValues;
        }
        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            // UserHolder description = "The User Holder V3";
            userHolder.setDescription("The User Holder V3");
            return userHolder;
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (ObjectUtils.nullSafeEquals("userHolder", beanName) && UserHolder.class.equals(bean.getClass())) {
            UserHolder userHolder = (UserHolder) bean;
            // init() The User Holder V6
            // UserHolder description = "The User Holder V7";
            userHolder.setDescription("The User Holder V7");
            return userHolder;
        }
        return bean;
    }
}
