package ru.krt.soap;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class DocumentTemplatePhaseHolder {
    private
    DocumentBuilderFactory documentBuilderFactory;
    private
    DocumentBuilder documentBuilder = null;
    private DOMImplementation DOMImpl= null;
    private Document documentStylesheet
            ,documentTemplate
            ,documentRequest
            ;

    public DocumentTemplatePhaseHolder() {
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        DOMImpl = documentBuilder.getDOMImplementation();
    }
    public DOMImplementation getDOMImpl() {
        return DOMImpl;
    }

/*
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

    public void createDocumentTemplateWithRootPrefix(String namespaceURI, String qualifiedName, DocumentType doctype) {
        documentTemplate = DOMImpl.createDocument(namespaceURI, qualifiedName, doctype);
    }

    public Document getDocumentRequest() {
        return documentRequest;
    }

    public void setDocumentRequest(Document documentRequest) {
        this.documentRequest = documentRequest;
    }
}
