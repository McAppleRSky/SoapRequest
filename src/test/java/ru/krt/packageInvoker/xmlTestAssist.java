package ru.krt.packageInvoker;

//import org.custommonkey.xmlunit.DetailedDiff;
//import org.custommonkey.xmlunit.Diff;
//import org.custommonkey.xmlunit.Difference;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class xmlTestAssist {

/*
    public static void showXmlDiff(Diff diff) {
        DetailedDiff detailedDiff = new DetailedDiff(diff);
        List differences = detailedDiff.getAllDifferences();
        for (Object object : differences) {

            Difference difference = (Difference) object;
            System.out.println("***********************");
            System.out.println(difference);
            System.out.println("***********************");
        }
    }
*/

    public boolean tryValid(String xsdFileName, String xmlFileName){
        boolean result = false;
        String localSchemaFile = xsdFileName//"envelope.xsd"
                ,
                localActualFile = xmlFileName,
                schemaFile = null;
        URL schemaFileLocation = null, actualFileLocation = null;
        schemaFileLocation = //new URL(webSchemaFile)
                ClassLoader.getSystemClassLoader().getResource(localSchemaFile)
        ;
        actualFileLocation =
                ClassLoader.getSystemClassLoader().getResource(localActualFile);
        Source xmlFile = new StreamSource( new File(actualFileLocation.getFile()) );
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(schemaFileLocation);
            Validator validator = schema.newValidator();
            validator.validate(xmlFile);
            result = true;
        } catch (SAXException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
