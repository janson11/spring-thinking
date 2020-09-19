package org.janson.think.in.spring.environment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * @Description: {@link TestPropertySource} 示例
 * @Author: Janson
 * @Date: 2020/9/19 18:31
 **/
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestPropertySourceTest.class)
@TestPropertySource(
        properties = "user.name=小单",
        locations = "classpath:/META-INF/default.properties")
public class TestPropertySourceTest {

    @Value("${user.name}")
    private String userName;

    @Autowired
    private ConfigurableEnvironment environment;

    @Test
    public void testUserName() {
        assertEquals("小单", userName);
        for (PropertySource<?> ps : environment.getPropertySources()) {
            System.out.printf("PropertySource(name=%s) 'user.name' 属性：%s\n", ps.getName(), ps.getProperty("user.name"));
        }
    }

}
