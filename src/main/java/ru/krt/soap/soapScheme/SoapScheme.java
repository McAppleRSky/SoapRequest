package ru.krt.soap.soapScheme;

import org.w3c.dom.Document;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public abstract class SoapScheme {
    protected String soapScheme_uri, soapSchemeMnemonic;

    protected static ArrayList<String> soapScheme = new ArrayList<>();

    public ArrayList<String> getSoapSchemes(){
        return soapScheme;
    }

    protected void addSoapScheme(String id, String namespace_uri){
        soapScheme.add(namespace_uri);
    }

    public String getSoapScheme_uri() {
        return soapScheme_uri;
    }
    public String getSoapSchemeMnemonic() {
        return soapSchemeMnemonic;
    }

    public abstract Document returnRequestTemplate();

}