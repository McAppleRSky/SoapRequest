package ru.krt.soap.artefactData;

import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import ru.krt.soap.types.plain.DocumentDomimpl;
import ru.krt.soap.soapScheme.AbstractSoapScheme;

import java.io.ByteArrayOutputStream;

public class VS00648001PFR001 extends AbstractSoapScheme {

    public VS00648001PFR001() {
        objectId = //new String[]{
                "http://kvs.pfr.com/snils-by-additionalData/1.0.1"
                //,"VS00648001PFR001"}
        ;
//        addObject(objectId[0]);
    }

    @Override
    public ByteArrayOutputStream mainMethod(Object... object) {
        //fillDocument();
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
    }

    @Override
    public String getObjectId() {
        return objectId;
    }

/*
//    private MessageId messageId;
//    public VS00648001PFR001() {
//        namespace_uri = "http://kvs.pfr.com/snils-by-additionalData/1.0.1";
//        id = "VS00648001PFR001";
//        addArtefactData(id, namespace_uri);
//    }
//    @Override
//    public ByteArrayOutputStream returnRequest(//int... operands
//                                        ) {
//        //fillDocument();
//                DocumentDomimpl documentDomimpl = null;
//        DOMImplementationLS domSaver = (DOMImplementationLS) documentDomimpl.getDOMImpl();
//
//        LSSerializer serializer = domSaver.createLSSerializer();
//        LSOutput load_save_outer = domSaver.createLSOutput();
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        load_save_outer.setByteStream(byteArrayOutputStream);
//        serializer.write(documentDomimpl.getDocumentTemplate(), //.getDocumentRequest(),
//                load_save_outer);
//        //serializer.write(documentTemplate, load_save_outer);
//
//        System.out.println();
//        return byteArrayOutputStream;}

    private final String[] prefixTip = new String[]{"ns","ns1","ns2","ns3","ns4","ns5","ns6","ns7","ns8","ns9"}
        ,nsTip = new String[]{
            "urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1"
            ,"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1"
        };
    private String prefix
            ,empty  = ""
            ,delimeter = ":"
            ,qualifiedName = "xmlns"
            ;

    NamespacePrefix[] namespaces;

*/
/*
    private void fillDocument(){
        messageId = new MessageId();
        //Wsdl1Testenv wsdl1testenv = new Wsdl1Testenv();
        PackageInvoker soapScheme = new PackageInvoker("ru.krt.soap.soapScheme", SoapScheme.class)
        DocumentDomimpl plainDocTemplate = wsdl1testenv.returnRequestTemplate();
        Element _MessageID
                = (Element) plainDocTemplate.getDocumentTemplate()
                    .getElementsByTagName(namespaces[0].getPrefix()+delimeter+"MessageID")
                .item(0)
                ;
        _MessageID.setTextContent( messageId.generate() );

        StreamSource stylesheetSource = new StreamSource("xslt.xsl");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
             documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        documentTransformation( new DOMSource( plainDocTemplate.getDocumentTemplate() );
    }
*//*
    private void documentTransformation(DOMSource documentSource) {
        */
/*Document sourceXMLDoc = documentBuilder.parse("src/test1/Sample1.xml");*//*
*/
/*
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer(stylesheetSource);
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        Document document = documentBuilder.newDocument();
        DOMResult documentResult = new DOMResult(document);
        try {
            transformer.transform(documentSource, documentResult);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        Document documentRequest = (Document) documentResult.getNode();}*/
}
