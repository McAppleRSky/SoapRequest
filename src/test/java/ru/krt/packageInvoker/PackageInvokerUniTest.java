package ru.krt.packageInvoker;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Ignore;
import org.junit.Test;
import org.reflections.Reflections;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;
import ru.krt.soap.types.plain.DocumentDomimpl;
import ru.krt.soap.soapScheme.AbstractSoapScheme;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Iterator;

import static org.junit.Assert.*;

public class PackageInvokerUniTest {

    @Test
    public void testSoapSchemePackage (){
        PackageInvoker packageInvoker = new PackageInvoker();
        packageInvoker
                .enumSoapScheme("soapScheme", new StringBuilder(),
                        new Reflections("ru.krt.soap.soapScheme" )
                .getSubTypesOf(AbstractSoapScheme.class) );
        assertEquals(2, packageInvoker.listPackageObject.get("soapScheme").size());
    }
    @Test
    public void testArtefactDataPackage (){
        PackageInvoker packageInvoker = new PackageInvoker();
        packageInvoker
                .enumSoapScheme("artefactData", new StringBuilder(),
                        new Reflections("ru.krt.soap.artefactData" )
                                .getSubTypesOf(AbstractSoapScheme.class) );
        assertEquals(1, packageInvoker.listPackageObject.get("artefactData").size());
    }
    @Test
    public void testSoapSchemeArtefactDataEnumInvoke (){
        PackageInvoker packageInvoker = new PackageInvoker();
        packageInvoker
                .enumSoapScheme("soapScheme", new StringBuilder(),
                        new Reflections("ru.krt.soap.soapScheme" )
                                .getSubTypesOf(AbstractSoapScheme.class) );
        packageInvoker
                .enumSoapScheme("artefactData", new StringBuilder(),
                        new Reflections("ru.krt.soap.artefactData" )
                                .getSubTypesOf(AbstractSoapScheme.class) );
        assertEquals( new DocumentDomimpl(null,null).getClass(), packageInvoker.invokeMain("soapScheme", "http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl").getClass() );
        //assertEquals( new DocumentDomimpl(null,null).getClass(), packageInvoker.invokeMain("artefactData", "http://kvs.pfr.com/snils-by-additionalData/1.0.1").getClass() );
    }

    @Test
    public void testSoapSchemeInvoke (){
        // https://turreta.com/2016/11/11/java-compare-xml-files-using-xmlunit/
        PackageInvoker packageInvoker = new PackageInvoker();
        packageInvoker
                .enumSoapScheme("soapScheme", new StringBuilder(),
                        new Reflections("ru.krt.soap.soapScheme" )
                                .getSubTypesOf(AbstractSoapScheme.class) );
        DocumentDomimpl wsdlTemplate = (DocumentDomimpl) packageInvoker.invokeMain("soapScheme", "http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl");

        DOMImplementationLS domSaver = (DOMImplementationLS) wsdlTemplate.getDOMImpl();
        LSSerializer serializer = domSaver.createLSSerializer();
        LSOutput load_save_outer = domSaver.createLSOutput();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        load_save_outer.setByteStream(byteArrayOutputStream);
        serializer.write(wsdlTemplate.getDocumentTemplate(), load_save_outer);
        byte[] actualBytes = byteArrayOutputStream.toByteArray();

        //load_save_outer.getByteStream().write(actualBytes);

        Reader expectedReader = null;
        String expectedString = null, actualString = null;
        Diff diff = null;
        try {
            expectedReader = new InputStreamReader(
                    new BOMInputStream(
                            new FileInputStream(
                                    ClassLoader
                                            .getSystemClassLoader()
                                            .getResource("request1.xml")
                                            .getFile()
                            ) ), Charset.forName("UTF-8") );
            XMLUnit.setIgnoreComments(true);
            XMLUnit.setIgnoreWhitespace(true);
            XMLUnit.setNormalize(true);
            XMLUnit.setIgnoreAttributeOrder(true);
            expectedString = IOUtils.toString(expectedReader);
            actualString = new String(actualBytes, StandardCharsets.UTF_8);
            diff = XMLUnit.compareXML(expectedString, actualString);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }

        StringBuffer sb = new StringBuffer();
        diff.appendMessage(sb);
        assertTrue(sb.toString(), diff.identical());

        File bytesFile = new File(
                ClassLoader
                        .getSystemClassLoader()
                        .getResource("actualFormRequest3.xml")
                        .getFile()
        );
        byte[] expectedBytes = new byte[(int) bytesFile.length()];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(bytesFile);
            fileInputStream.read(expectedBytes);
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //StringMaker stringMaker = new StringMaker( expectedBytes.toString() );
        String expectedXML = new String(expectedBytes, StandardCharsets.UTF_8)
//                stringMaker.removeWhitespace().toString();
                //StringUtils.deleteWhitespace(  )
                ;
        //stringMaker.setString( actualBytes.toString() );
        //String actualXML =
//                stringMaker.removeWhitespace().getString();
//                StringUtils.deleteWhitespace(
//                        new String(actualBytes, StandardCharsets.UTF_8)
                ;

/*
        Diff diff = DiffBuilder.compare(Input.fromString(expectedXML)).withTest(Input.fromString(actualXML))
                .checkForSimilar()
                .ignoreWhitespace()
                .build();
        assertFalse("XML similar " + myDiff.toString(), myDiff.hasDifferences());
*/
        //assertTrue(MessageFormat.format("XML must be simular: {0}\nActual XML:\n{1}\n", diff, actualXML), myDiff.similar());

        //assertArrayEquals();
        //assertEquals(expectedBytes.toString(), bytes.toString());
        //assertTrue(bytes.toString().equals(expectedBytes.toString()));
        //assertTrue(Arrays.equals(expectedBytes, bytes));
        System.out.println();
    }

    @Test
    @Ignore
    public void testInvoke1 (){
        PackageInvoker
                packageInvoker = new PackageInvoker(//AbstractSoapScheme.class
        );
        DocumentDomimpl documentDomImpl = (DocumentDomimpl)packageInvoker.invokeMain("", "http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl");
        DOMImplementationLS domSaver = (DOMImplementationLS) documentDomImpl.getDOMImpl();
        LSSerializer load_save_serializer = domSaver.createLSSerializer();
        LSOutput load_save_outer = domSaver.createLSOutput();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        load_save_outer.setByteStream(byteArrayOutputStream);
        load_save_serializer.write(documentDomImpl.getDocumentTemplate(), load_save_outer);

        File file = new File("actualFormRequest3.xml");
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
//    @Test
//    public void testActualXml (){
//
//    }

    @Test
    //@Ignore
    public void testActualFiles (){
        ClassLoader classLoader = this.getClass().getClassLoader();
//        File file = new File(classLoader.getResource("file.name").getFile());
        File expectedFile = new File(classLoader.getResource("actualFormRequest3.xml").getFile())
                ,actualFile = new File(classLoader.getResource("actualFormRequest2.xml").getFile())
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

        Document expectedDocument = null, actualDocument = null
                //expectedDocumentInput = null, actualDocumentInput = null
                //,expectedDocumentStore = null, actualDocumentStore = null
                ;
        try {
            expectedDocument = documentBuilder.parse(expectedByteArrayInputStream);
            actualDocument = documentBuilder.parse(actualByteArrayInputStream);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream expectedByteArrayOutputStream = new ByteArrayOutputStream()
                ,actualByteArrayOutputStream = new ByteArrayOutputStream()
                ;
        //transformer.transform(new DOMSource(document), new StreamResult(out));
        try {
            transformer.transform(new DOMSource(expectedDocument), new StreamResult(expectedByteArrayOutputStream));
            transformer.transform(new DOMSource(actualDocument), new StreamResult(actualByteArrayOutputStream));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        String expectedString = expectedByteArrayOutputStream.toString()
                ,actualString = actualByteArrayOutputStream.toString()
                ;
/*
        try {
            XMLAssert.assertXMLEqual(expectedString, actualString);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
*/


//        expectedDocument.normalizeDocument();
//        actualDocument.normalizeDocument();
//        assertTrue(expectedDocument.isEqualNode(actualDocument));

/*
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

//        expectedDocument.getDocumentElement().normalize();
//        actualDocument.getDocumentElement().normalize();

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
*/

        // note https://stackoverflow.com/questions/139076/how-to-pretty-print-xml-from-java
/*
        DefaultNodeMatcher nodeMatcher = new DefaultNodeMatcher(ElementSelectors//.byNameAndText
                                                                    .Default
                                                                        );

        Diff diff = DiffBuilder//.compare(xmlSource1).withTest(xmlSource2)
                //.compare(expectedString).withTest(actualString)
                .compare(expectedDocument).withTest(actualDocument)
                .checkForSimilar()
                .withNodeMatcher(nodeMatcher)
                .ignoreWhitespace()
                .ignoreComments()
                .build();
*/
          // https://m.habr.com/ru/post/127473/
    }

}
