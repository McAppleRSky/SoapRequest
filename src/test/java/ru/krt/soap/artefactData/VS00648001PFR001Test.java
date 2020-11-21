package ru.krt.soap.artefactData;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class VS00648001PFR001Test extends AbstractPackageEnumeratorAndSaxHandler {

    @Test
    public void testImportSnilsChengeNsFaultsFillMsgId_Diff2 () {
        VS00648001PFR001 snilsRequestUno = new VS00648001PFR001();
        byte[] expectedBytes = null, aclualBeforeBytes = null;
        Diff diff = null;
        expectedBytes =
                bytesFromResources(
                        "templatesActualMock/wsdlRequestImportSnilsChengeNsFaultsFillMsgId.xml"
                );
        aclualBeforeBytes =
                bytesFromResources(
                        "templatesActualMock/wsdlRequestImportSnilsChengeNsFaults.xml"
                );
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document expectedDocument = Convert.toDocument(Input.fromByteArray(expectedBytes).build(), documentBuilderFactory);

        ImplDomDocument aclualWsdlRequestTemplateImportSnilsChengeNsFaultsFillMsgIdPlain = snilsRequestUno
                .wsdlRequestTemplateImportSnilsChengeNsFaults_fillMsgId(
                        snilsRequestUno.implDomDocumentFromBytes(aclualBeforeBytes)
                );

        Document actualDocument = aclualWsdlRequestTemplateImportSnilsChengeNsFaultsFillMsgIdPlain.getDocument();
        diff = DiffBuilder.compare(expectedDocument).withTest(actualDocument)
                .checkForSimilar()
                .ignoreComments()
                .ignoreWhitespace()
                .build();
        Iterator<Difference> iter = diff.getDifferences().iterator();
        assertTrue( diff.hasDifferences() );
        assertTrue(  iter.hasNext() );
        String regex_uuid = "[a-z0-9]{8}[\\-][a-z0-9]{4}[\\-][a-z0-9]{4}[\\-][a-z0-9]{4}[\\-][a-z0-9]{12}",
                regex = "^Expected text value '" + regex_uuid
                        + "' but was '" + regex_uuid
                        +"' - comparing <ns2:MessageID ...>" + regex_uuid
+"</ns2:MessageID> at /Envelope[\\[]1[\\]]/Body[\\[]1[\\]]/SendRequestRequest[\\[]1[\\]]/SenderProvidedReq"
        +"uestData[\\[]1[\\]]/MessageID[\\[]1[\\]]/text[\\(][\\)][\\[]1[\\]] to <ns2:MessageID ...>"
                        + regex_uuid
+"</ns2:MessageID> at /Envelope[\\[]1[\\]]/Body[\\[]1[\\]]/SendRequestRequest[\\[]1[\\]]/SenderProvidedReq"
                        +"uestData[\\[]1[\\]]/MessageID[\\[]1[\\]]/text[\\(][\\)][\\[]1[\\]] [\\(]DIFFERENT[\\)]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher( iter.next().toString() );
        assertTrue( matcher.matches() );
        //assertEquals("Expected text value ", iter.next().toString().split("^", 1)[0] );
        assertFalse(  iter.hasNext() );
    }

    @Test
    public void testImportSnilsChengeNsFaults_Diff2 () {
        VS00648001PFR001 snilsRequestUno = new VS00648001PFR001();
        byte[] expectedBytes = null, aclualBeforeBytes = null;
        Diff diff = null;
        expectedBytes =
                bytesFromResources(
                        "templatesActualMock/wsdlRequestImportSnilsChengeNsFaults.xml"
                );
        aclualBeforeBytes =
                bytesFromResources(
                        "templatesActualMock/wsdlRequestImportSnilsChengeNs.xml"
                );
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document expectedDocument = Convert.toDocument(Input.fromByteArray(expectedBytes).build(), documentBuilderFactory);

        ImplDomDocument aclualWsdlRequestTemplateImportSnilsChengeNsFaultsPlain = snilsRequestUno
                .wsdlRequestTemplateImportSnilsChengeNs_chengeFaults(
                    snilsRequestUno.implDomDocumentFromBytes(aclualBeforeBytes, false)
        );

        Document actualDocument = aclualWsdlRequestTemplateImportSnilsChengeNsFaultsPlain.getDocument();
        diff = DiffBuilder.compare(expectedDocument).withTest(actualDocument)
                .checkForSimilar()
                .ignoreComments()
                .ignoreWhitespace()
                .build();
        Iterator<Difference> iter = diff.getDifferences().iterator();
        assertFalse( diff.hasDifferences() );
    }

    @Test
    public void testChengeNs_Diff2 () {
        VS00648001PFR001 snilsRequestUno = new VS00648001PFR001();
        byte[] expectedBytes = null, aclualBeforeBytes = null;
        Diff diff = null;
        expectedBytes =
                bytesFromResources(
                        "templatesActualMock/wsdlRequestImportSnilsChengeNs.xml"
                );
        aclualBeforeBytes =
                bytesFromResources(
                        "templatesActualMock/wsdlRequestImportSnils.xml"
                );
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document expectedDocument = Convert.toDocument(Input.fromByteArray(expectedBytes).build(), documentBuilderFactory);

        ImplDomDocument aclualPlainDocumentWithoutNsAware = snilsRequestUno.wsdlRequestTemplateImportSnils_chengeNs(
                snilsRequestUno.implDomDocumentFromBytes(aclualBeforeBytes)
        );
        byte[] bytesWithoutNsAware = snilsRequestUno.implDomDocumentToBytes(aclualPlainDocumentWithoutNsAware);
        ImplDomDocument aclualWsdlRequestTemplateImportSnilsWithNsAwarePlain = snilsRequestUno.implDomDocumentFromBytes(bytesWithoutNsAware);
        Document actualDocument = aclualWsdlRequestTemplateImportSnilsWithNsAwarePlain.getDocument();
        diff = DiffBuilder.compare(expectedDocument).withTest(actualDocument)
                .checkForSimilar()
                .ignoreComments()
                .ignoreWhitespace()
                .build();
        Iterator<Difference> iter = diff.getDifferences().iterator();
        assertFalse( diff.hasDifferences() );
    }

    @Test
    public void testImportSnils_Diff2 () {
        VS00648001PFR001 snilsRequestUno = new VS00648001PFR001();
        byte[] expectedBytes = null, aclualBeforeBytes = null;
        Diff diff = null;
        expectedBytes = //packageInvokerWrap.fromFileReturnBytes(
                bytesFromResources(
                        "templatesActualMock/wsdlRequestImportSnils.xml"
                );
        aclualBeforeBytes = //packageInvokerWrap.fromFileReturnBytes(
                bytesFromResources(
                        "templatesActualMock/wsdlRequest1.xml"
                );

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document expectedDocument = Convert.toDocument(Input.fromByteArray(expectedBytes).build(), documentBuilderFactory);

        ImplDomDocument aclualWsdlRequestTemplateImportSnils = snilsRequestUno.wsdlRequestTemplate_importSnils(
                snilsRequestUno.implDomDocumentFromBytes(aclualBeforeBytes)
        );

        Document actualDocument = aclualWsdlRequestTemplateImportSnils.getDocument();
        diff = DiffBuilder.compare(expectedDocument).withTest(actualDocument)
                .checkForSimilar()
                .ignoreComments()
                .ignoreWhitespace()
                .build();
        Iterator<Difference> iter = diff.getDifferences().iterator();
        assertFalse( diff.hasDifferences() );
    }

}
