package ru.krt.packageInvoker.back;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public interface IPackageEnumerator {

    void addObject(String objectId);
    public ArrayList<String> getObjects();

    void addObjectMap(String... objectId);
    public HashMap<String, String> getObjectsMap();

    public String getObjectId();

    public abstract Object mainMethod();

}
