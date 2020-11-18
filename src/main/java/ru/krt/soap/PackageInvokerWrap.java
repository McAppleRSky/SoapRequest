package ru.krt.soap;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.BOMInputStream;
import org.reflections.Reflections;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import ru.krt.packageInvoker.PackageInvoker;
import ru.krt.soap.soapScheme.AbstractSoapScheme;
import ru.krt.soap.types.plain.DocumentDomImpl;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PackageInvokerWrap {

    PackageInvoker packageInvoker = null;

    public Object artefactDataReturn(String packageName, String prefix, String objectId, Object... argument) {
        Object result = null;
        packageInvoker
                .enumSoapScheme( packageName, new StringBuilder(),
                        new Reflections(prefix)
                                .getSubTypesOf(AbstractSoapScheme.class) );
        result = packageInvoker.invokeMain(packageName, objectId, argument);
        return result;
    }

    public Object soapSchemeReturn(String packageName, String prefix, String objectId) {

        // https://turreta.com/2016/11/11/java-compare-xml-files-using-xmlunit/

        Object result = null;
        packageInvoker = new PackageInvoker();
        packageInvoker
                .enumSoapScheme( packageName, new StringBuilder(),
                        new Reflections(prefix)
                                .getSubTypesOf(AbstractSoapScheme.class) );
        result = packageInvoker.invokeMain(packageName, objectId);
        return result;
    }

    public byte[] soapSchemeReturnBytes(String packageName, String prefix, String objectId){
        byte[] result = null;
        DocumentDomImpl wsdlTemplate = null;
        wsdlTemplate = (DocumentDomImpl)soapSchemeReturn(packageName, prefix, objectId);
/*
        PackageInvoker packageInvoker = new PackageInvoker();
        packageInvoker
                .enumSoapScheme(packageName, new StringBuilder(),
                        new Reflections(prefix)
                                .getSubTypesOf(AbstractSoapScheme.class) );
        DocumentDomImpl wsdlTemplate = (DocumentDomImpl) packageInvoker.invokeMain("soapScheme", "http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl");
*/
        DOMImplementationLS domSaver = (DOMImplementationLS) wsdlTemplate.getDOMImpl();
        LSSerializer serializer = domSaver.createLSSerializer();
        LSOutput load_save_outer = domSaver.createLSOutput();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        load_save_outer.setByteStream(byteArrayOutputStream);
        serializer.write(wsdlTemplate.getDocumentTemplate(), load_save_outer);
        result = byteArrayOutputStream.toByteArray();
        if(result==null)throw new NullPointerException("Can't return bytes from dom");
        return result;
    }

    public Reader soapSchemeReturnReader (String packageName, String prefix, String objectId){
        Reader reader = null;
        reader = new InputStreamReader(
                new ByteArrayInputStream(
                        soapSchemeReturnBytes(packageName, prefix, objectId) ) );
        return reader;
    }

    public String soapSchemeReturnString(String packageName, String prefix, String objectId){
        return new String(soapSchemeReturnBytes(packageName, prefix, objectId), StandardCharsets.UTF_8);
    }

    public Reader fromFileReader(String name) throws IOException {
        Reader reader = null;
/*
        String fileName = ClassLoader.getSystemClassLoader().getResource(name).getFile();
        File file = new File(fileName);
        InputStream inputStream = new DataInputStream( new FileInputStream(file) );
        reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
*/

        reader = new InputStreamReader(
                new BOMInputStream(
                        new FileInputStream(
                                ClassLoader
                                        .getSystemClassLoader()
                                        .getResource(name)
                                        .getFile()
                        )
                )
                , Charset.forName("UTF-8") );
        return reader;
    }

    public String fromFile(String name) throws IOException {
        String result = IOUtils.toString(
                fromFileReader(name) );
        if(result==null)throw new NullPointerException("Can't return bytes");
        return result;
    }

    public byte[] fromFileReturnBytes(String name) {
        byte [] result = null;
        try {
            result = Files.readAllBytes(Paths.get( ClassLoader
                    .getSystemClassLoader()
                    .getResource(name)
                    //.getFile()
                    .toURI() ))
            ;
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        if(result==null) throw new NullPointerException();
        return result;
    }

}
