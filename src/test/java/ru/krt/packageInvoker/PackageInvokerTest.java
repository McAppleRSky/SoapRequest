package ru.krt.packageInvoker;

import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import ru.krt.soap.artefactData.ArtefactData;
import ru.krt.soap.plainTypes.DocumentDomimpl;
import ru.krt.soap.soapScheme.SoapScheme;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

import static org.junit.Assert.*;

public class PackageInvokerTest {

    @Test
    public void testList (){
        PackageInvoker
                packageInvoker = new PackageInvoker(SoapScheme.class)
                ,packageInvoker1 = new PackageInvoker(ArtefactData.class);
        assertEquals(2, packageInvoker.listObject.size());
        assertEquals(1, packageInvoker1.listObject.size());
    }

    @Test
    public void testInvoke (){
        PackageInvoker
                packageInvoker = new PackageInvoker(SoapScheme.class);
        assertEquals(new DocumentDomimpl(null,null).getClass(), packageInvoker.invokeMain( "http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl").getClass() );
    }

    @Test
    @Ignore
    public void testInvoke1 (){
        PackageInvoker
                packageInvoker = new PackageInvoker(SoapScheme.class);
        DocumentDomimpl documentDomimpl = (DocumentDomimpl)packageInvoker.invokeMain( "http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl");
        DOMImplementationLS domSaver = (DOMImplementationLS) documentDomimpl.getDOMImpl();
        LSSerializer load_save_serializer = domSaver.createLSSerializer();
        LSOutput load_save_outer = domSaver.createLSOutput();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        load_save_outer.setByteStream(byteArrayOutputStream);
        load_save_serializer.write(documentDomimpl.getDocumentTemplate(), load_save_outer);

        File file = new File("actualFormRequest.xml");
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



/*
    DocumentDomimpl documentDomimpl = null;
    DOMImplementationLS domSaver = (DOMImplementationLS) documentDomimpl.getDOMImpl();
    LSSerializer serializer = domSaver.createLSSerializer();
    LSOutput load_save_outer = domSaver.createLSOutput();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    load_save_outer.setByteStream(byteArrayOutputStream);

        serializer.write(documentDomimpl.getDocumentTemplate(), //.getDocumentRequest(),
    load_save_outer);
    //serializer.write(documentTemplate, load_save_outer);
        System.out.println();
        return byteArrayOutputStream;
*/

    @Test
    public void testActualFiles (){
        File expectedFile = new File("actualFormRequest3.xml")
                ,actualFile = new File("actualFormRequest2.xml")
                ;
        FileInputStream expectedFileInputStream = null
                ,actualFileInputStream = null
                ;
        byte[] expectedBytes_forRead = new byte[(int) expectedFile.length()]
                ,actualBytes_forRead = new byte[(int) actualFile.length()]
                ;
        try {
            expectedFileInputStream = new FileInputStream(expectedFile);
            expectedFileInputStream.read(expectedBytes_forRead);
            expectedFileInputStream.close();

            actualFileInputStream = new FileInputStream(actualFile);
            actualFileInputStream.read(actualBytes_forRead);
            actualFileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayInputStream expectedByteArrayInputStream = new ByteArrayInputStream(expectedBytes_forRead)
                ,actualByteArrayInputStream = new ByteArrayInputStream(actualBytes_forRead)
                ;

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = null;
        DOMImplementation domImpl = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        domImpl = documentBuilder.getDOMImplementation();

        Document expectedDocument = null, actualDocument = null;
        try {
            expectedDocument = documentBuilder.parse(expectedByteArrayInputStream);
            actualDocument = documentBuilder.parse(actualByteArrayInputStream);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        DOMImplementationLS domSaver = (DOMImplementationLS) domImpl;
        LSSerializer load_save_serializer = domSaver.createLSSerializer();
        LSOutput load_save_outer_forExpected = domSaver.createLSOutput()
                ,load_save_outer_forActual = domSaver.createLSOutput()
                ;

        ByteArrayOutputStream expectedByteArrayOutputStream = new ByteArrayOutputStream()
                ,actualByteArrayOutputStream = new ByteArrayOutputStream()
                ;

        load_save_outer_forExpected.setByteStream(expectedByteArrayOutputStream);
        load_save_outer_forActual.setByteStream(actualByteArrayOutputStream);

        load_save_serializer.write(expectedDocument, load_save_outer_forExpected);
        load_save_serializer.write(actualDocument, load_save_outer_forActual);

        byte[] expectedBytes_forWrite = new byte[(int) expectedFile.length()]
                ,actualBytes_forWrite = new byte[(int) actualFile.length()]
                ;
        try {
            load_save_outer_forExpected.getByteStream().write(expectedBytes_forWrite);
            load_save_outer_forActual.getByteStream().write(actualBytes_forWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertArrayEquals(expectedBytes_forWrite, actualBytes_forWrite);
    }

}
