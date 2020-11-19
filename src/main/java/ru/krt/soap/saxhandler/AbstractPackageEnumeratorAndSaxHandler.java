package ru.krt.soap.saxhandler;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.krt.packageInvoker.AbstractPackageEnumerator;
import ru.krt.soap.types.plain.ImplDomDocument;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AbstractPackageEnumeratorAndSaxHandler //extends AbstractPackageEnumerator
                                                                {

    public byte[] bytesFromResources(String resourceName){
        byte[] result = null;
        try {
            result = Files.readAllBytes(
                    Paths.get(
                            ClassLoader
                                    .getSystemClassLoader()
                                    .getResource(resourceName)
                                    //.getFile()
                                    .toURI()
                    )
            );
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        if(result==null) throw new NullPointerException("Can't load bytes from " + resourceName);
        return result;
    }

    protected ImplDomDocument implDomDocumentFromBytes(byte[] bytes){
        ImplDomDocument result = null;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = null;
        DOMImplementation implDom = null;
        Document document = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            document = documentBuilder.parse(
                    new InputSource( new ByteArrayInputStream(bytes) )
            );
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        implDom = documentBuilder.getDOMImplementation();
        if (implDom == null || document  == null) throw new NullPointerException("Nullable DOM implementation of Form Request");
        return new ImplDomDocument(document, implDom);
    }

    protected byte[] implDomDocumentToBytes(ImplDomDocument implDomDocument){
        return implDomDocumentToBytes( implDomDocument.getDocument(), implDomDocument.getImplDom() );
    }

    protected byte[] implDomDocumentToBytes(Document document, DOMImplementation domImpl){
        byte[] bytes = null;
        DOMImplementationLS domSaver = (DOMImplementationLS) domImpl;
        LSSerializer saveSerializer = domSaver.createLSSerializer();
        LSOutput saveOuter = domSaver.createLSOutput();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        saveOuter.setByteStream(byteArrayOutputStream);
        saveSerializer.write(document, saveOuter);
        bytes = byteArrayOutputStream.toByteArray();
        if(bytes==null) throw new NullPointerException("Cant return bytes from document");
        return bytes;
    }
}
