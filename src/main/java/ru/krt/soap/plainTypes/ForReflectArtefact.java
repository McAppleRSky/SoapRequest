package ru.krt.soap.plainTypes;

import java.lang.reflect.Method;

public class ForReflectArtefact {
    private Object instance;
    private Method method;

    public ForReflectArtefact(Object instance) {
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
