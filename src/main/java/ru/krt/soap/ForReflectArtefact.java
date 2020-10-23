package ru.krt.soap;

import java.lang.reflect.Method;

public class ForReflectArtefact {
    private Object instance;
    private Method method;

/*
    public ForReflectArtefact(Object instance, Method method) {
        this.instance = instance;
        this.method = method;
    }
*/
    public ForReflectArtefact(Object instance) {
        this.instance = instance;
        //this.method = method;
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
