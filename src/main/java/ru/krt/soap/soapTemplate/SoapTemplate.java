package ru.krt.soap.soapTemplate;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public abstract class SoapTemplate {
    //protected String namespace_uri, id;

    protected static ArrayList<String> artefactsData = new ArrayList<>();

    public ArrayList<String> getArtefacts(){
        return artefactsData;
    }

    protected void addSoapTemplate(String id, String namespace_uri){
        artefactsData.add(namespace_uri);
    }

    public String getNamespace_uri() {
        return namespace_uri;
    }

    public abstract ByteArrayOutputStream returnTemplate();

}
