package ru.krt.packageInvoker;

import org.reflections.Reflections;
import ru.krt.packageInvoker.back.PlainObject;
import ru.krt.soap.soapScheme.SoapScheme;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class PackageInvoker {
    protected String mainMethod = "mainMethod"
            ,getObjectId = "getObjectId"
            ,getObjects = "getObjects"
            ;
    protected Class<?> clazzClass = null;
//    protected PlainObject plainObject = null;
    protected HashMap<String, PlainObject> listObject = new HashMap<>();
    protected ArrayList<String> listObjectId = null;
    protected Class[] paramTypes //= null;
             = (Class<?>[]) null;
    protected Object[] param //= null;
             = new Object[]{};

    public PackageInvoker(Class classType) {
        if(classType == null) throw new NullPointerException("absent class - type of object");
        else packageEnum(classType.getPackageName(), classType);
    }

    private void packageEnum(String prefixPackage, Class classType){
        PlainObject plainObject = null;
        Set < Class < ? extends SoapScheme> >
                classes = new Reflections(prefixPackage).getSubTypesOf(classType);
        Iterator < Class <? extends SoapScheme> >
                clazz = classes.iterator();
        while (clazz.hasNext()) {
            try {
                clazzClass = Class.forName( clazz.next().getName() );
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                plainObject = new PlainObject( clazzClass.getDeclaredConstructor().newInstance() );
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            //paramTypes = (Class<?>[]) null; // дубль
            try {
                plainObject.setMethod(clazzClass.getMethod(mainMethod, paramTypes));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            //paramTypes = (Class<?>[]) null; // дубль
            //param = new Object[]{}; // дубль
            try {
                listObject.put( (String) clazzClass
                                .getMethod(getObjectId, paramTypes)
                                .invoke(plainObject.getInstance(), param),
                        plainObject );
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
            //param = new Object[]{}; // дубль
            try {
                listObjectId = (ArrayList<String>)
                        clazzClass
                                .getMethod(getObjects, paramTypes)
                                .invoke(plainObject.getInstance(), param);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    };

    public Object invokeMain(String objectId) {
        PlainObject plainObject = null;
        Object result = null;
        if ( !listObjectId.contains(objectId) ) throw new NullPointerException("Absent " + objectId);
        else
            try {
                plainObject = listObject.get(objectId);
                result = plainObject.getMethod().invoke(
                        plainObject.getInstance(), param
                );
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        if (result==null) throw new NullPointerException("invokeMain return null for: " + objectId + " caller:" + this.getClass() );
        return result;
    }

}
