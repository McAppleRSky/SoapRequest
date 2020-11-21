package ru.krt.soap.soapScheme;

import junit.framework.TestCase;
import org.junit.Ignore;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;
import org.xmlunit.util.Convert;
import ru.krt.soap.saxhandler.AbstractPackageEnumeratorAndSaxHandler;
import ru.krt.soap.types.plain.ImplDomDocument;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Iterator;

import static org.junit.Assert.assertFalse;

public class Wsdl1TestenvTest extends AbstractPackageEnumeratorAndSaxHandler {

    @Test
    //@Ignore
    public void testWsdl1Testenv_Diff2 (){
        Wsdl1Testenv wsdl1Testenv = new Wsdl1Testenv();
        byte[] expectedBytes;
        Diff diff = null;
        AbstractPackageEnumeratorAndSaxHandler saxHandler = new AbstractPackageEnumeratorAndSaxHandler();
        expectedBytes = //packageInvokerWrap.fromFileReturnBytes(
                saxHandler.bytesFromResources(
                        "templatesActualMock/wsdlRequest1.xml"
                );
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document expectedDocument = Convert.toDocument(Input.fromByteArray(expectedBytes).build(), documentBuilderFactory);
        ImplDomDocument aclualPlainDocument = implDomDocumentFromBytes( implDomDocumentToBytes( wsdl1Testenv.templateRequestFormEnvelopeSample() ) );
        Document actualDocument = aclualPlainDocument.getDocument();
        diff = DiffBuilder.compare(expectedDocument).withTest(actualDocument)
                .checkForSimilar()
                .ignoreComments()
                .ignoreWhitespace()
                .build();
        Iterator<Difference> iter = diff.getDifferences().iterator();
        assertFalse( diff.hasDifferences() );
    }

}
