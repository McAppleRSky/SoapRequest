package ru.krt.packageInvoker.back;

import java.lang.reflect.Method;

public class PlainObject {
    private Object instance;
    private Method method;

    public PlainObject(Object instance) {
        this.instance = instance;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getInstance() {
        return instance;
    }

    public Method getMethod() {
        return method;
    }

}
