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
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class PackageInvokerWrap {

    public byte[] soapSchemeReturnBytes(String packageName, String prefix){
        byte[] result = null;
        // https://turreta.com/2016/11/11/java-compare-xml-files-using-xmlunit/
        PackageInvoker packageInvoker = new PackageInvoker();
        packageInvoker
                .enumSoapScheme(packageName, new StringBuilder(),
                        new Reflections(prefix)
                                .getSubTypesOf(AbstractSoapScheme.class) );
        DocumentDomImpl wsdlTemplate = (DocumentDomImpl) packageInvoker.invokeMain("soapScheme", "http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl");
        DOMImplementationLS domSaver = (DOMImplementationLS) wsdlTemplate.getDOMImpl();
        LSSerializer serializer = domSaver.createLSSerializer();
        LSOutput load_save_outer = domSaver.createLSOutput();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        load_save_outer.setByteStream(byteArrayOutputStream);
        serializer.write(wsdlTemplate.getDocumentTemplate(), load_save_outer);
        result = byteArrayOutputStream.toByteArray();
        if(result==null)throw new NullPointerException("Can't return bytes");
        return result;
    }

    public Reader soapSchemeReturnReader (String packageName, String prefix){
        Reader reader = null;
        reader = new InputStreamReader(
                new ByteArrayInputStream(
                        soapSchemeReturnBytes(packageName, prefix) ) );
        return reader;
    }

    public String soapSchemeReturnString(String packageName, String prefix){
        return new String(soapSchemeReturnBytes(packageName, prefix), StandardCharsets.UTF_8);
    }

    public Reader fromFileReader(String name) throws IOException {
        Reader reader = null;
        reader = new InputStreamReader(
                new BOMInputStream(
                        new FileInputStream(
                                ClassLoader
                                        .getSystemClassLoader()
                                        .getResource(name)
                                        .getFile()
                        ) ), Charset.forName("UTF-8") );

        return reader;
    }

    public String fromFile(String name) throws IOException {
        String result = IOUtils.toString(
                fromFileReader(name) );
        if(result==null)throw new NullPointerException("Can't return bytes");
        return result;
    }

}
