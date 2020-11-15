package ru.krt.soap.types.plain;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class DocumentDomImpl {
    private DOMImplementation domImpl;
    private Document documentTemplate;

    public DocumentDomImpl(DOMImplementation domImpl, Document documentTemplate) {
        this.domImpl = domImpl;
        this.documentTemplate = documentTemplate;
    }

    public DOMImplementation getDOMImpl() {
        return domImpl;
    }

    public void setDOMImpl(DOMImplementation domImpl) {
        this.domImpl = domImpl;
    }

    public Document getDocumentTemplate() {
        return documentTemplate;
    }

    public void setDocumentTemplate(Document documentTemplate) {
        this.documentTemplate = documentTemplate;
    }

}
