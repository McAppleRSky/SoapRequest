package ru.krt.packageInvoker;

import org.junit.Ignore;
import org.junit.Test;
import org.reflections.Reflections;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;
//import ru.krt.soap.PackageInvokerWrap;
import ru.krt.soap.artefactData.AbstractArtefactData;
import ru.krt.soap.soapScheme.AbstractSoapScheme;
import ru.krt.soap.types.plain.ImplDomDocument;

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

import static junitx.framework.FileAssert.assertBinaryEquals;
import static org.junit.Assert.*;

public class PackageInvokerUniTest extends xmlTestAssist {

    @Test
    public void testSoapSchemePackage (){
        PackageInvoker packageInvoker = new PackageInvoker();
        packageInvoker
                .enumSoapScheme("soapScheme", new StringBuilder(),
                        new Reflections("ru.krt.soap.soapScheme")
                .getSubTypesOf(AbstractSoapScheme.class) );
        assertEquals(1, packageInvoker.listPackageObject.get("soapScheme").size() );
    }
    @Test
    public void testArtefactDataPackage (){
        PackageInvoker packageInvoker = new PackageInvoker();
        packageInvoker
                .enumArtefactData("artefactData", new StringBuilder(),
                        new Reflections("ru.krt.soap.artefactData")
                .getSubTypesOf(AbstractArtefactData.class) );
        assertEquals(1, packageInvoker.listPackageObject.get("artefactData").size() );
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
        assertEquals( new ImplDomDocument(null,null).getClass(), packageInvoker.invokeMain("soapScheme", "http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl").getClass() );
        //assertEquals( new DocumentDomimpl(null,null).getClass(), packageInvoker.invokeMain("artefactData", "http://kvs.pfr.com/snils-by-additionalData/1.0.1").getClass() );
    }
    @Test
    public void testValidUseXml (){
        String etolonRequest = "templatesActualMock/wsdlRequest1.xml";
        assertTrue( tryValid("schemas.xmlsoap.org.xml", etolonRequest) );
        //assertTrue( tryValid("xml-artefact/1/smev-message-exchange-basic-1.1.xsd", etolonRequest) );
        //assertTrue( tryValid("xml-artefact/1/smev-message-exchange-types-1.1.xsd", etolonRequest) );
        //assertTrue( tryValid("xml-artefact/1/smev-message-exchange-faults-1.1.xsd", etolonRequest) );
        assertTrue( tryValid("schemas.xmlsoap.org.xml", "prevOutput.xml") );
        //assertTrue( tryValid("xml-artefact/1/smev-message-exchange-basic-1.1.xsd", "prevOutput.xml") );
        // cvc-elt.1.a: Cannot find the declaration of element
        assertTrue( tryValid("schemas.xmlsoap.org.xml", "templatesActualMock/wsdlRequest1.xml") );
    }
    @Test
    @Ignore
    public void testSomeXmlGenerationPrimitivEquals() throws IOException {
        String xmlString = null;
        // здесь подготавливаются данные для генератора
        //xmlString = someModule.generateXML();
        // а теперь нешуточное сравнение XML данных
        File file = File.createTempFile("actial_data", ".xml");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(xmlString);
        fileWriter.close();
        File fileExpect = new File(this.getClass().getResource("/expected_data.xml").getFile());
        //FileAssert.
        assertBinaryEquals(fileExpect, file);
    }

    @Test
    @Ignore
    public void testDomSoapSchemeOutFile (){
        String packageName = "soapScheme", objectId = "http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl",
                prefix = "ru.krt.soap.soapScheme";
        PackageInvoker packageInvoker = new PackageInvoker();
        packageInvoker
                .enumSoapScheme(packageName, new StringBuilder(),
                        new Reflections(prefix)
                                .getSubTypesOf(AbstractSoapScheme.class) );
        ImplDomDocument documentImplementated = (ImplDomDocument)packageInvoker.invokeMain(packageName, objectId);
        DOMImplementationLS domSaver = (DOMImplementationLS) documentImplementated.getImplDom();
        LSSerializer save_serializer = domSaver.createLSSerializer();
        LSOutput save_outer = domSaver.createLSOutput();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        save_outer.setByteStream(byteArrayOutputStream);
        save_serializer.write(documentImplementated.getDocument(), save_outer);

        File file = new File("prevOutput.xml");
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

    @Test
    @Ignore
    public void testActualFiles (){
        ClassLoader classLoader = this.getClass().getClassLoader();
//        File file = new File(classLoader.getResource("file.name").getFile());
        File expectedFile = new File(classLoader.getResource("actualFormRequest3.xml").getFile())
                ,actualFile = new File(classLoader.getResource("actualFormRequest2.xml").getFile())
                ;
        //URL url = PackageInvokerUniTest.class.getResource("actualFormRequest2.xml");
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
    }

}
