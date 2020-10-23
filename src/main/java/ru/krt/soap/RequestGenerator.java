package ru.krt.soap;

import ru.krt.soap.artefactData.ArtefactData;
import org.reflections.Reflections;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
//    protected Object artefactInstance = null;
    protected Class[] //paramTypes = null;
                        paramTypes = (Class<?>[]) null;
    protected Object[] //param = null;
                        param = new Object[]{};
    protected HashMap<String, Method> method = new HashMap<>();
    protected HashMap<String, ForReflectArtefact> listNamespaceArtefact = new HashMap<>();
    protected ArrayList<String> listNamespaces = null;
    protected String returnRequest = "returnRequest"
            ,getNamespace_uri = "getNamespace_uri"
            ,getArtefacts = "getArtefacts"
            //,getArtefactsData = "getArtefactsData"
            //,getArtefacts = "getArtefacts"
            ;
    protected ForReflectArtefact forReflectArtefact = null;
    private String namesspace_uri;

    public RequestGenerator() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Set<Class<? extends ArtefactData>> artefacts = new Reflections(prefixPackage).getSubTypesOf(ArtefactData.class);
        if (artefacts.size() <= 0) throw new NullPointerException("Far too few artefactData");
        else {
            Iterator<Class<? extends ArtefactData>> artefact = artefacts.iterator();
            while (artefact.hasNext()) {
                artefactClass = Class.forName(artefact.next().getName());
                forReflectArtefact = new ForReflectArtefact( artefactClass.getDeclaredConstructor().newInstance() );
                //paramTypes = (Class<?>[]) null; // дубль
                forReflectArtefact.setMethod(artefactClass.getMethod(returnRequest, paramTypes));
                //paramTypes = (Class<?>[]) null; // дубль
                //param = new Object[]{}; // дубль
                listNamespaceArtefact.put( (String) artefactClass
                                                        .getMethod(getNamespace_uri, paramTypes)
                                                        .invoke(forReflectArtefact.getInstance(), param),
                                                    forReflectArtefact );
            }

            //param = new Object[]{}; // дубль
            listNamespaces = (ArrayList<String>)
                    artefactClass
                            .getMethod(getArtefacts, paramTypes)
                            .invoke(forReflectArtefact.getInstance(), param);
        }
    }

    protected void setArtefactData(String namesspace_uri) {
        this.namesspace_uri = namesspace_uri;
    }

    public ByteArrayOutputStream generate() {
        ByteArrayOutputStream result = null;
        if( ! listNamespaces.contains(namesspace_uri) ) return result;
        forReflectArtefact = listNamespaceArtefact.get(namesspace_uri);
        try {
            result = (ByteArrayOutputStream) forReflectArtefact.getMethod().invoke(forReflectArtefact.getInstance(), param);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
}
