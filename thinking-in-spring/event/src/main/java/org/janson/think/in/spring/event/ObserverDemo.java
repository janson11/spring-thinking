package org.janson.think.in.spring.event;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

/**
 * @Description: {@link java.util.Observer} 示例
 * @Author: Janson
 * @Date: 2020/9/7 23:34
 **/
public class ObserverDemo {
    public static void main(String[] args) {
        Observable observable = new EventObservable();
        // 添加观察者(监听者)
        observable.addObserver(new EventObserver());
        // 发布消息(事件)
        observable.notifyObservers("Hello ,world");
    }

    static class EventObservable extends Observable {
        @Override
        protected synchronized void setChanged() {
            super.setChanged();
        }

        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            super.notifyObservers(new EventObject(arg));
            clearChanged();
        }
    }

    static class EventObserver implements Observer, EventListener {

        @Override
        public void update(Observable o, Object message) {
            EventObject eventObject = (EventObject) message;
            System.out.println("收到事件：" + eventObject);
        }
    }
}
