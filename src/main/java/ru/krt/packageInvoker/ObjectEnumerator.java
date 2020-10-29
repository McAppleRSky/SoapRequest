package ru.krt.packageInvoker;

import java.util.ArrayList;

public abstract class ObjectEnumerator implements ObjectEnumeratorExtender {

    protected String id, name;

    //    protected static HashMap<String, String> artefactsData = new HashMap<>();
    protected static ArrayList<String> artefactsData = new ArrayList<>();

    @Override
    public void addObject(String... idName) {
        artefactsData.add(idName[0]);
    }

    @Override
    public Object getIdName() {
        return id;
    }

    public abstract Object mainMethod();
}
