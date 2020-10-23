package ru.krt.soap.artefactData;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class ArtefactData {
    protected String namespace_uri, id;

//    protected static HashMap<String, String> artefactsData = new HashMap<>();
    protected static ArrayList<String> artefactsData = new ArrayList<>();

    public ArrayList<String> getArtefacts(){
        return artefactsData;
    }

    protected void addArtefactData(String id, String namespace_uri){
        artefactsData.add(namespace_uri);
    }

    public String getNamespace_uri() {
        return namespace_uri;
    }

    public abstract ByteArrayOutputStream returnRequest();

}
