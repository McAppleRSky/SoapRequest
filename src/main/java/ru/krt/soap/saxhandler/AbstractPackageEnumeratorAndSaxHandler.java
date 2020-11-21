package ru.krt.soap.saxhandler;

import org.reflections.Reflections;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.krt.packageInvoker.PackageInvoker;
import ru.krt.soap.soapScheme.AbstractSoapScheme;
import ru.krt.soap.types.plain.ImplDomDocument;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AbstractPackageEnumeratorAndSaxHandler implements SaxDatatypeUse {

    private boolean namespaceAware = true;

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

    public ImplDomDocument implDomDocumentFromBytes(byte[] bytes, boolean... namespaceAware){
        if(namespaceAware.length>0) this.namespaceAware = namespaceAware[0];
        ImplDomDocument result = null;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(this.namespaceAware);
        this.namespaceAware = true;
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

    public byte[] implDomDocumentToBytes(ImplDomDocument implDomDocument){
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

    public void implDomDocumentToFile(ImplDomDocument implDomDocument, String pathname){
        implDomDocumentToFile(implDomDocument.getDocument(), implDomDocument.getImplDom(), pathname);
    }

    protected void implDomDocumentToFile(Document document, DOMImplementation implDom, String pathname) {
        File file = new File(pathname);
        DOMImplementationLS domSaver = (DOMImplementationLS) implDom;
        LSSerializer save_serializer = domSaver.createLSSerializer();
        LSOutput save_outer = domSaver.createLSOutput();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        save_outer.setByteStream(byteArrayOutputStream);
        save_serializer.write(document, save_outer);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            byteArrayOutputStream.writeTo(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
