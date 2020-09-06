package org.janson.think.in.spring.conversion;

/**
 * @Description:
 * @Author: Janson
 * @Date: 2020/9/6 9:59
 **/
public class PropertyEditorDemo {

    public static void main(String[] args) {
        // 模拟spring framework 操作
        String text = "name=小单";
        StringToPropertiesPropertyEditor propertyEditor = new StringToPropertiesPropertyEditor();
        propertyEditor.setAsText(text);
        System.out.println(propertyEditor.getValue());
    }
}
