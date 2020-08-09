package org.janson.think.in.spring.bean.scope;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.core.NamedThreadLocal;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: ThreadLocal 级别Scope
 * @Author: Janson
 * @Date: 2020/8/9 9:50
 **/
public class ThreadLocalScope implements Scope {
    public static final String SCOPE_NAME = "thread-local";

    private final NamedThreadLocal<Map<String, Object>> threadLocal = new NamedThreadLocal("thread-local-scope") {
        @Override
        protected Object initialValue() {
            return new HashMap<>();
        }
    };

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Map<String, Object> context = threadLocal.get();
        Object object = context.get(name);
        if (object == null) {
            object = objectFactory.getObject();
            context.put(name, object);
        }
        return object;
    }

    @NonNull
    private Map<String, Object> getContext() {
        return threadLocal.get();
    }


    @Override
    public Object remove(String name) {
        Map<String, Object> context = getContext();
        return context.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // TODO
    }

    @Override
    public Object resolveContextualObject(String key) {
        Map<String, Object> context = getContext();
        return context.get(key);
    }

    @Override
    public String getConversationId() {
        Thread thread = Thread.currentThread();
        return String.valueOf(thread.getId());
    }
}
