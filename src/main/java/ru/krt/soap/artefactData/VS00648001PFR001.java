package ru.krt.soap.artefactData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

import java.io.ByteArrayOutputStream;

public class VS00648001PFR001 extends ArtefactData {

    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private DOMImplementation dom;
    private Document document;

    public VS00648001PFR001() {
        namespace_uri = "http://kvs.pfr.com/snils-by-additionalData/1.0.1";
        id = "VS00648001PFR001";
        addArtefactData(id, namespace_uri);
        //result = new String[1];
    }

    private void fillDocument(){
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        dom = documentBuilder.getDOMImplementation();
        document = dom.createDocument("urn:example.namespace", "my:example", null);
        Element element = document.createElementNS("http://another.namespace", "element");
        document.getDocumentElement().appendChild(element);
    }

    @Override
    public ByteArrayOutputStream returnRequest(//int... operands
                                        ) {
        fillDocument();
        DOMImplementationLS domSaver = (DOMImplementationLS) dom;

        LSSerializer serializer = domSaver.createLSSerializer();
        LSOutput outer = domSaver.createLSOutput();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        outer.setByteStream(byteArrayOutputStream);
        serializer.write(document, outer);

        System.out.println();
        return byteArrayOutputStream;
    }
}
