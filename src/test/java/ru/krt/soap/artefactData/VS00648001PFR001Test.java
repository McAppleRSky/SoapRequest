package ru.krt.soap.artefactData;

import junit.framework.TestCase;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;
import org.xmlunit.util.Convert;
import ru.krt.soap.saxhandler.AbstractPackageEnumeratorAndSaxHandler;
import ru.krt.soap.soapScheme.Wsdl1Testenv;
import ru.krt.soap.types.plain.ImplDomDocument;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Iterator;

import static org.junit.Assert.assertFalse;

public class VS00648001PFR001Test extends AbstractPackageEnumeratorAndSaxHandler {
    @Test
    public void testSnilsRequestUno_Diff2 () {
        VS00648001PFR001 snilsRequestUno = new VS00648001PFR001();
        byte[] expectedBytes = null, aclualBeforeBytes = null;
        Diff diff = null;
        expectedBytes = //packageInvokerWrap.fromFileReturnBytes(
                bytesFromResources(
                        "expectedSnilsRequesBeforeUuid.xml"
                );
        aclualBeforeBytes = //packageInvokerWrap.fromFileReturnBytes(
                bytesFromResources(
                        "wsdlRequest1.xml"
                );

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document expectedDocument = Convert.toDocument(Input.fromByteArray(expectedBytes).build(), documentBuilderFactory);

        ImplDomDocument aclualPlainDocument = snilsRequestUno.fillTemplateBySnilsSample(
                snilsRequestUno.implDomDocumentFromBytes(aclualBeforeBytes)
        );

        Document actualDocument = aclualPlainDocument.getDocument();
        diff = DiffBuilder.compare(expectedDocument).withTest(actualDocument)
                .checkForSimilar()
                .ignoreComments()
                .ignoreWhitespace()
                .build();
        Iterator<Difference> iter = diff.getDifferences().iterator();
        assertFalse( diff.hasDifferences() );
        System.out.println();
    }
}