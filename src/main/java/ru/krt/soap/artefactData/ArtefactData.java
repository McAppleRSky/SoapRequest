package ru.krt.soap.artefactData;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class ArtefactData {
    protected String namespace_uri, id;

//    protected static HashMap<String, String> artefactsData = new HashMap<>();
    protected static ArrayList<String> artefactsData = new ArrayList<>();

    protected void addArefactData(String id, String namespace_uri){
        artefactsData.add(namespace_uri);
    }

    public abstract String[] returnSoapPocket(int ... operands);

}
