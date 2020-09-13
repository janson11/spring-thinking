package org.janson.think.in.spring.annotation;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Description: 偶数Profile
 * @Author: Janson
 * @Date: 2020/9/13 14:57
 **/
public class EvenProfileCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // 条件上下文
        Environment environment = context.getEnvironment();
        return environment.acceptsProfiles("even");
    }
}
