package org.janson.think.in.spring.data.binding;

import org.janson.think.in.spring.ioc.overview.domain.Company;
import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: {@link org.springframework.validation.DataBinder}示例
 * @Author: Janson
 * @Date: 2020/9/5 12:32
 **/
public class DataBinderDemo {
    public static void main(String[] args) {
        // 创建空白对象
        User user = new User();
        // 1 创建DataBinder
        DataBinder dataBinder = new DataBinder(user, "user");
        //2  创建PropertyValues
        Map<String, Object> source = new HashMap<>();
        source.put("id", 1);
        source.put("name", "小单");

        //a  PropertyValues存在User中不存在的属性值
        // DataBinder 特性一：忽略未知属性
        source.put("age", 18);
        // b  PropertyValues存在User的嵌套属性company.name
        // DataBinder 特性二：支持嵌套属性
        source.put("company", new Company());
        source.put("company.name", "org.com");
        PropertyValues propertyValues = new MutablePropertyValues(source);
        // 调整 IgnoreInvalidFields true (默认) ->false (抛出异常，age字段不存在于User类)
        // dataBinder.setIgnoreUnknownFields(false);
        // 调整IgnoreInvalidFields false(默认) ->true (默认情况调整不变化,需调整AutoGrowNestedPaths为false)
        dataBinder.setAutoGrowNestedPaths(false);
//        dataBinder.setIgnoreInvalidFields(true);

        dataBinder.setRequiredFields("id", "name", "city");

        dataBinder.bind(propertyValues);
        // 3 输出User内容
        System.out.println(user);
        // 4、获取绑定结果(结果包含错误文案code，不会抛出异常)
        BindingResult bindingResult = dataBinder.getBindingResult();
        System.out.println(bindingResult);

    }
}
