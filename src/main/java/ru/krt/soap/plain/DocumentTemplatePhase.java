package ru.krt.soap.plain;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class DocumentTemplatePhase {
    //private DOMImplementation DOMImpl;
    private Document documentStylesheet
            ,documentTemplate
            ,documentRequest
            ;

/*
    public DOMImplementation getDOMImpl() {
        return DOMImpl;
    }

    public void setDOMImpl(DOMImplementation DOMImpl) {
        this.DOMImpl = DOMImpl;
    }
*/

    public Document getDocumentStylesheet() {
        return documentStylesheet;
    }

    public void setDocumentStylesheet(Document documentStylesheet) {
        this.documentStylesheet = documentStylesheet;
    }

    public Document getDocumentTemplate() {
        return documentTemplate;
    }

    public void setDocumentTemplate(Document documentTemplate) {
        this.documentTemplate = documentTemplate;
    }

    public Document getDocumentRequest() {
        return documentRequest;
    }

    public void setDocumentRequest(Document documentRequest) {
        this.documentRequest = documentRequest;
    }
}
