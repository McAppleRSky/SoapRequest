package ru.krt.packageInvoker;


import org.reflections.Reflections;
import ru.krt.packageInvoker.back.PlainObject;
import ru.krt.soap.artefactData.AbstractArtefactData;
import ru.krt.soap.soapScheme.AbstractSoapScheme;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class PackageInvoker {
    protected String mainMethod = "mainMethod"
            ,getObjectId = "getObjectId"
            ;
    protected Class<?>
            clazzClass = null;
    protected HashMap<String, PlainObject> listObject = new HashMap<>();
    private Class[] mainMethodParamTypes = new Class[]{Object[].class}
            ,emptyParamTypes = (Class<?>[]) null
            ;
    protected Class[] paramTypes = null;
    private Object[] emptyParam = new Object[]{}
            ;
    protected Object[] param = null;

    public PackageInvoker(Class classType) {
        if(classType == null) throw new NullPointerException("Absent class - type of object");
        else packageEnumUnoStatic(classType.getPackageName(), classType);
    }
    private void packageEnumUnoStatic(String prefixPackage, Class classType){
        PlainObject plainObject = null;
        Set< Class < ?
                extends AbstractSoapScheme
                >
                >
                classes = new Reflections(prefixPackage).getSubTypesOf(classType);
        Iterator< Class <?
                extends AbstractSoapScheme
                >
                >
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
            paramTypes = mainMethodParamTypes;
            try {
                plainObject.setMethod(clazzClass.getMethod(mainMethod, paramTypes));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            paramTypes = emptyParamTypes;
            param = emptyParam;
            try {
                listObject.put(
                        (String) clazzClass
                                .getMethod(getObjectId, paramTypes)
                                .invoke(plainObject.getInstance(), param )
                        ,plainObject
                );
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    };

    public Object invokeMain(String objectId, Object... argument) {
        PlainObject plainObject = null;
        Object result = null;
        if ( !listObject.containsKey(objectId) ) throw new NullPointerException("Absent object (id : " + objectId + ")");
        else
            try {
                plainObject = listObject.get(objectId);
                param = new Object[]{argument};
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
