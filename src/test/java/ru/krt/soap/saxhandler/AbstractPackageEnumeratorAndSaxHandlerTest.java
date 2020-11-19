package ru.krt.soap.saxhandler;

import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;
import org.xmlunit.util.Convert;
import ru.krt.soap.soapScheme.Wsdl1Testenv;
import ru.krt.soap.types.plain.ImplDomDocument;

import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import static org.junit.Assert.*;

public class AbstractPackageEnumeratorAndSaxHandlerTest extends AbstractPackageEnumeratorAndSaxHandler{


    @Test
    public void loadFromFileBytesSnilsTest(){
        byte[] bytes = null;
        bytes = bytesFromResources("xml-artefact/VS00648001PFR001/Request0.xml");
        assertNotNull(bytes);
    }
    @Test
    public void EnvelopeFromBytesTest(){
        ImplDomDocument
                implDomDocument
                    = implDomDocumentFromBytes(
                            bytesFromResources(
                                    "xml-samples/envelope.xml"
                            )
        );
        assertNotNull(implDomDocument);
        assertNotNull( implDomDocument.getDocument() );
        assertNotNull( implDomDocument.getImplDom() );
    }
    @Test
    public void ImplDomDocumentToBytesEnvelopeTest(){
        byte[] bytes = null;
        ImplDomDocument implDomDocument = implDomDocumentFromBytes(
                bytesFromResources(
                        "xml-samples/envelope.xml"
                )
        );
        bytes = implDomDocumentToBytes(
                implDomDocument.getDocument()
                ,implDomDocument.getImplDom()
        );
        assertNotNull(bytes);
    }

    @Test
    @Ignore
    public void testImport(){
        ImplDomDocument requestSnilsSample = null, wsdlTemplate = implDomDocumentFromBytes(
                bytesFromResources("wsdlRequest1.xml")
        );
        Document requestTemplateDocument = null, requestSnilsSampledocument = null;
        requestTemplateDocument = wsdlTemplate.getDocument();
        requestSnilsSample =  implDomDocumentFromBytes(
                bytesFromResources("Request0.xml")
        );
        requestSnilsSampledocument = requestSnilsSample.getDocument();
        Node _SnilsByAdditionalDataRequest = requestSnilsSampledocument.getElementsByTagNameNS("http://kvs.pfr.com/snils-by-additionalData/1.0.1",
                "SnilsByAdditionalDataRequest").item(0);
        Node importedNode_SnilsByAdditionalDataRequest = requestTemplateDocument.importNode(
                _SnilsByAdditionalDataRequest
                ,true);

        Element _MessagePrimaryContent = (Element)requestTemplateDocument.getElementsByTagNameNS(
                "urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1",
                "MessagePrimaryContent").item(0)
                //,_SnilsByAdditionalDataRequest = (Element)requestSnilsSampledocument.getElementsByTagNameNS(objectId,"SnilsByAdditionalDataRequest").item(0)
                ;
        _MessagePrimaryContent.appendChild(importedNode_SnilsByAdditionalDataRequest);
        byte[] actual = implDomDocumentToBytes( requestTemplateDocument, wsdlTemplate.getImplDom() );
        String string = new String(actual, StandardCharsets.UTF_8);
        assertArrayEquals(new byte[0], actual);
    }

}
