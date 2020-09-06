package org.janson.think.in.spring.conversion;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

/**
 * @Description: String ->Properties
 * @Author: Janson
 * @Date: 2020/9/6 10:00
 **/
public class StringToPropertiesPropertyEditor extends PropertyEditorSupport implements PropertyEditor {

    // 1 、实现setAsText方法
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        // 2、将String类型转换成Properties类型
        Properties properties = new Properties();
        try {
            properties.load(new StringReader(text));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        // 临时存储Properties对象
        setValue(properties);
    }
}
