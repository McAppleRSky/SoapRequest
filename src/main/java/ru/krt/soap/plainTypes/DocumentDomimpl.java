package ru.krt.soap.plainTypes;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

public class DocumentDomimpl {
    private DOMImplementation DOMImpl;
    private Document documentTemplate;

    public DocumentDomimpl(DOMImplementation DOMImpl, Document documentTemplate) {
        this.DOMImpl = DOMImpl;
        this.documentTemplate = documentTemplate;
    }

    public DOMImplementation getDOMImpl() {
        return DOMImpl;
    }

    public void setDOMImpl(DOMImplementation DOMImpl) {
        this.DOMImpl = DOMImpl;
    }

    public Document getDocumentTemplate() {
        return documentTemplate;
    }

    public void setDocumentTemplate(Document documentTemplate) {
        this.documentTemplate = documentTemplate;
    }

}
