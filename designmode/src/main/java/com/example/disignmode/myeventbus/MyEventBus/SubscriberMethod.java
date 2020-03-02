package com.example.disignmode.myeventbus.MyEventBus;

import android.icu.text.PluralRules;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Method;

/**
 * created by linghaoDo on 2019-11-18
 * description:
 * <p>
 * version:
 */
public class SubscriberMethod {
    final Method method;
    final ThreadMode threadMode;
    final Class<?> eventType;
    final int priority;
    private boolean sticky;
    String methodString;

    public SubscriberMethod(Method method, Class<?> eventType, ThreadMode threadMode, int priority, boolean sticky) {
        this.method = method;
        this.threadMode = threadMode;
        this.eventType = eventType;
        this.priority = priority;
        this.sticky = sticky;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof SubscriberMethod) {
            cheeckMethodString();
            SubscriberMethod otherSubscriberMethod = (SubscriberMethod) obj;
            otherSubscriberMethod.cheeckMethodString();
            return methodString.equals(otherSubscriberMethod.methodString);
        } else
            return false;
    }

    private synchronized void cheeckMethodString() {
        if (methodString == null) {
            StringBuilder builder = new StringBuilder(64);
            builder.append(method.getDeclaringClass().getName());
            builder.append('#').append(method.getName());
            builder.append('(').append(eventType.getName());
            methodString = builder.toString();
        }
    }


    @Override
    public int hashCode() {
        return method.hashCode();
    }
}
