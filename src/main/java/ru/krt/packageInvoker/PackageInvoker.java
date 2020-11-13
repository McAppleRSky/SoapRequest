package ru.krt.packageInvoker;


import org.reflections.Reflections;
import ru.krt.packageInvoker.type.EnumeratedArtefactData;
import ru.krt.packageInvoker.type.plain.PlainObject;
import ru.krt.packageInvoker.type.EnumeratedSoapScheme;
import ru.krt.soap.artefactData.AbstractArtefactData;
import ru.krt.soap.soapScheme.AbstractSoapScheme;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class PackageInvoker implements EnumeratedSoapScheme, EnumeratedArtefactData {
    protected String mainMethod = "mainMethod"
            ,getObjectId = "getObjectId"
            ;
    protected Class<?>
            clazzClass = null;
    //protected HashMap<String, PlainObject> listObject = new HashMap<>();
    protected HashMap<String, HashMap<String, PlainObject>> listPackageObject = new HashMap<>();
    private Class[] mainMethodParamTypes = new Class[]{Object[].class}
            ,emptyParamTypes = (Class<?>[]) null
            ;
    protected Class[] paramTypes = null;
    private Object[] emptyParam = new Object[]{}
            ;
    protected Object[] param = null;
/*
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


    public PackageInvoker(String[] classNames) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        */
/*//*
/ ИЛИ
        ClassLoader c = this.getClassLoader();*//*

        PlainObject plainObject = null;
        for (String className : classNames){
            try {
                clazzClass = classLoader.loadClass(className);
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
    }

*/
    public Object invokeMain(String packageName, String objectId, Object... argument) {
        PlainObject plainObject = null;
        Object result = null;
        if ( !listPackageObject.get(packageName).containsKey(objectId) ) throw new NullPointerException("Absent object (id : " + objectId + ")");
        else
            try {
                plainObject = listPackageObject.get(packageName).get(objectId);
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

/*
    public void PackageInvoker(String packageName, String[] classNames) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        PlainObject plainObject = null;
        for (String className : classNames){
            try {
                clazzClass = classLoader.loadClass(className);
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
    }
*/

    private void commonEnumPackage(String packageName, StringBuilder classNamesBuilder){
        listPackageObject.put( packageName, new HashMap<>() );
        String[] classNames = Arrays.stream(
                classNamesBuilder
                        .toString()
                        .split(regexClass) )
                .filter( x -> !x.isEmpty() )
                .toArray(String[]::new)
        ;
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        // ИЛИ ClassLoader c = this.getClassLoader();
        PlainObject plainObject = null;
        for (String className : classNames){
            try {
                clazzClass = classLoader.loadClass(className);
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
                listPackageObject.get(packageName)
                        .put(
                        (String) clazzClass
                                .getMethod(getObjectId, paramTypes)
                                .invoke(plainObject.getInstance(), param )
                        ,plainObject
                );
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
    private final String regexClass = " class ", regexClassPrefix = " ", msgClassEnumException = "No classes package enum";
    @Override
    public void enumSoapScheme(String packageName, StringBuilder classNamesBuilder, Set<Class<? extends AbstractSoapScheme>> classes) {
        classes.forEach( c -> {
            try {classNamesBuilder.append(regexClassPrefix).append( Class.forName( c.getName() ) );
            } catch (ClassNotFoundException e) {e.printStackTrace();}
        });
        commonEnumPackage(packageName, classNamesBuilder);
    }

    @Override
    public void enumArtefactData(String packageName, StringBuilder classNamesBuilder, Set<Class<? extends AbstractArtefactData>> classes) {
        classes.forEach( c -> {
            try {classNamesBuilder.append(regexClassPrefix).append( Class.forName( c.getName() ) );
            } catch (ClassNotFoundException e) {e.printStackTrace();}
        });
        commonEnumPackage(packageName, classNamesBuilder);
    }

}
