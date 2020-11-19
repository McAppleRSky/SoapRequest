package ru.krt.soap.types.plain;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class ImplDomDocument {
    private Document document;
    private DOMImplementation implDom;

    public ImplDomDocument(Document documentTemplate, DOMImplementation domImpl) {
        this.implDom = domImpl;
        this.document = documentTemplate;
    }

    public DOMImplementation getImplDom() {
        return implDom;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

}
