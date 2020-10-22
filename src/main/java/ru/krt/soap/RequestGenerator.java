package ru.krt.soap;

import ru.krt.soap.artefactData.ArtefactData;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestGenerator {
    private static final java.util.logging.Logger logger;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        logger = Logger.getLogger(RequestGenerator.class.getName());
        logger.setLevel(Level.WARNING);
    }

    protected String prefixPackage = "ru.krt.soap.artefactData";
    protected Class<?> artefactClass = null;
    protected Object artefactInstance = null;
    protected Class[] paramTypes = null;
    protected Object[] param = null;
    protected HashMap<String, Method> method = new HashMap<>();
    protected String returnSoapPocket = "returnSoapPocket";

    public RequestGenerator() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Set<Class<? extends ArtefactData>> artefacts = new Reflections(prefixPackage).getSubTypesOf(ArtefactData.class);
        if (artefacts.size() <= 0) throw new NullPointerException("Far too few artefactData");
        else {
            Iterator<Class<? extends ArtefactData>> artefact = artefacts.iterator();
            while (artefact.hasNext()) {
                artefactClass = Class.forName(artefact.next().getName());
                artefactInstance = artefactClass.getDeclaredConstructor().newInstance();
                if (!method.containsKey(getMnemonic)) {
                    paramTypes = (Class<?>[]) null;
                    method.put(getMnemonic, artefactClass.getMethod(getMnemonic, paramTypes));
                }
                paramTypes = new Class[]{int[].class};
                method.put(returnSoapPocket, artefactClass.getMethod(returnSoapPocket, paramTypes));
                param = new Object[]{};
                Integer mn = 0;
                mn = (Integer) method.get(getMnemonic).invoke(artefactInstance, param);
                plainOperation = new PlainOperation(artefactInstance, method.get(returnSoapPocket));
                listMnemonicsOperations.put(mn, plainOperation);
            }
            paramTypes = (Class<?>[]) null;
            method.put(getMnemonics, artefactClass.getMethod(getMnemonics, paramTypes));
            paramTypes = new Class[]{char.class};
            method.put(genMnemonic, artefactClass.getMethod(genMnemonic, paramTypes));
            param = new Object[]{};
            listSymbolsMnemonics = (HashMap<Character, Integer>) method.get(getMnemonics).invoke(operationInstance, param);
        }
    }

    protected void setArtefactData(String arg) {
    }
}
