package ru.krt.packageInvoker;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public interface ObjectEnumeratorExtender {

    //    protected static HashMap<String, String> artefactsData = new HashMap<>();

    void addObject(String... idName);

    public Object getIdName();

    public abstract Object mainMethod();

}
