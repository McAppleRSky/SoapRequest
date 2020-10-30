package ru.krt.packageInvoker;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class PackageEnumerator implements IPackageEnumerator {

    protected String[] objectId;

    protected static ArrayList<String> packageObjects;
    @Override
    public void addObject(String objectId) {
        packageObjects.add(objectId);
    }
    @Override
    public ArrayList<String> getObjects(){
        return packageObjects;
    }

    protected static HashMap<String, String> packageObjectsMap;
    @Override
    public void addObjectMap(String... entity) {
        packageObjectsMap.put(entity[0], entity[1]);
    }
    @Override
    public HashMap<String, String> getObjectsMap(){
        return packageObjectsMap;
    }

    @Override
    public String getObjectId() {
        return objectId[0];
    }

    public abstract Object mainMethod();
}
