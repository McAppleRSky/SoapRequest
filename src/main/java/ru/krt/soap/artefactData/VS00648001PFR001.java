package ru.krt.soap.artefactData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import ru.krt.soap.MessageId;
import ru.krt.soap.plain.DocumentTemplatePhase;
import ru.krt.soap.plain.NamespacePrefix;

import java.io.ByteArrayOutputStream;

public class VS00648001PFR001 extends ArtefactData {

/*
    private DOMImplementation DOMImpl;
    private Document documentStylesheet
            ,documentTemplate
            ,documentRequest
            ;
*/

    private MessageId messageId;

    DocumentTemplatePhase documentTemplatePhase;

    public VS00648001PFR001() {
        namespace_uri = "http://kvs.pfr.com/snils-by-additionalData/1.0.1";
        id = "VS00648001PFR001";
        addArtefactData(id, namespace_uri);
    }

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

    private DOMImplementation DOMImpl;
    NamespacePrefix[] namespaces;


    private void wsdl_1_1_requestTemplateDomGenerator(String ...prefixExternal){
        NamespacePrefix soapenv = new NamespacePrefix("soapenv");
        soapenv.setNamespace("http://schemas.xmlsoap.org/soap/envelope/");
        //NamespacePrefix[]
                namespaces = new NamespacePrefix[prefixTip.length];
        for(int i=0;i<prefixTip.length;i++)
            namespaces[i]=new NamespacePrefix(prefixTip[i]);
        for(int i=0;i< prefixExternal.length;i++)
            namespaces[i].setPrefix(prefixExternal[i]);
        for(int i=0;i< nsTip.length;i++)
            namespaces[i].setNamespace(nsTip[i]);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        documentTemplatePhase = new DocumentTemplatePhase();

        DOMImpl =
        DOMImpl(documentBuilder.getDOMImplementation());

        documentTemplatePhase.setDocumentTemplate(
                documentTemplatePhase.getDOMImpl().createDocument(soapenv.getNamespace()
                        ,soapenv.getPrefix()+delimeter+"Envelope"
                        , null)
        );
        Element _Envelope = documentTemplate.getDocumentElement()
                ,_Header = documentTemplate.createElement(soapenv.getPrefix()+delimeter+"Header")
                ,_Body = documentTemplate.createElement(soapenv.getPrefix()+delimeter+"Body");
        _Envelope.setAttribute(qualifiedName+delimeter+namespaces[0].getPrefix(), namespaces[0].getNamespace());
        _Envelope.setAttribute(qualifiedName+delimeter+namespaces[1].getPrefix(), namespaces[1].getNamespace());
        _Envelope.appendChild(_Header);
        _Envelope.appendChild(_Body);
        prefix = namespaces[0].getPrefix();
        Element _SendRequestRequest
                    = documentTemplate.createElement(prefix+delimeter+"SendRequestRequest")
                ,_SenderProvidedRequestData
                    = documentTemplate.createElement(prefix+delimeter+"SendRequestRequestData")
                ,_MessageID
                    = documentTemplate.createElement(prefix+delimeter+"MessageID")
                ,_ReferenceMessageID
                    = documentTemplate.createElement(prefix+delimeter+"ReferenceMessageID")
                ,_TransactionCode
                    = documentTemplate.createElement(prefix+delimeter+"TransactionCode")
                ,_NodeID
                    = documentTemplate.createElement(prefix+delimeter+"NodeID")
                ,_EOL
                    = documentTemplate.createElement(prefix+delimeter+"EOL")
                ;
        _Body.appendChild(_SendRequestRequest)
            .appendChild(_SenderProvidedRequestData)
            .appendChild(_MessageID);
        _SenderProvidedRequestData.setAttribute("Id", empty);
        _SenderProvidedRequestData.appendChild(_ReferenceMessageID);
        _SenderProvidedRequestData.appendChild(_TransactionCode);
        _SenderProvidedRequestData.appendChild(_NodeID);
        _SenderProvidedRequestData.appendChild(_EOL);
        _SenderProvidedRequestData.appendChild(_EOL);
        prefix=namespaces[1].getPrefix();
        Element _MessagePrimaryContent
                = documentTemplate.createElement(prefix+delimeter+"MessagePrimaryContent")
                ;
        _SenderProvidedRequestData.appendChild(_MessagePrimaryContent);
        prefix=namespaces[0].getPrefix();
        Element _PersonalSignature
                = documentTemplate.createElement(prefix+delimeter+"PersonalSignature")
                ;
        _SenderProvidedRequestData.appendChild(_PersonalSignature);
        prefix=namespaces[1].getPrefix();
        Element _AttachmentHeaderList
                    = documentTemplate.createElement(prefix+delimeter+"AttachmentHeaderList")
                ,_AttachmentHeader
                    = documentTemplate.createElement(prefix+delimeter+"AttachmentHeader")
                ,_contentId
                    = documentTemplate.createElement(prefix+delimeter+"contentId")
                ,_MimeType
                    = documentTemplate.createElement(prefix+delimeter+"MimeType")
                ,_SignaturePKCS7
                    = documentTemplate.createElement(prefix+delimeter+"SignaturePKCS7");
        _SenderProvidedRequestData.appendChild(_AttachmentHeaderList)
                .appendChild(_AttachmentHeader)
                .appendChild(_contentId);
        _AttachmentHeader.appendChild(_MimeType);
        _AttachmentHeader.appendChild(_SignaturePKCS7);

        Element _RefAttachmentHeaderList
                    = documentTemplate.createElement(prefix+delimeter+"RefAttachmentHeaderList")
                ,_RefAttachmentHeader
                    = documentTemplate.createElement(prefix+delimeter+"RefAttachmentHeader")
                ,_uuid
                    = documentTemplate.createElement(prefix+delimeter+"RefAttachmentHeader")
                ,_Hash
                    = documentTemplate.createElement(prefix+delimeter+"RefAttachmentHeader")
                ,_MimeType_ref
                    = documentTemplate.createElement(prefix+delimeter+"RefAttachmentHeader")
                ,_SignaturePKCS7_ref
                    = documentTemplate.createElement(prefix+delimeter+"SignaturePKCS7")
                ;
        _SenderProvidedRequestData.appendChild(_RefAttachmentHeaderList)
                .appendChild(_RefAttachmentHeader)
                .appendChild(_uuid);
        _RefAttachmentHeader.appendChild(_Hash);
        _RefAttachmentHeader.appendChild(_MimeType_ref);
        _RefAttachmentHeader.appendChild(_SignaturePKCS7_ref);
        prefix = namespaces[0].getPrefix();
        Element _BusinessProcessMetadata
                    = documentTemplate.createElement(prefix+delimeter+"BusinessProcessMetadata")
                ,_TestMessage
                    = documentTemplate.createElement(prefix+delimeter+"TestMessage");
        _SenderProvidedRequestData.appendChild(_BusinessProcessMetadata);
        _SenderProvidedRequestData.appendChild(_TestMessage);
        prefix = namespaces[1].getPrefix();
        Element _AttachmentContentList
                    = documentTemplate.createElement(prefix+delimeter+"AttachmentContentList")
                ,_AttachmentContent
                    = documentTemplate.createElement(prefix+delimeter+"AttachmentContent")
                ,_Id
                    = documentTemplate.createElement(prefix+delimeter+"Id")
                ,_Content
                    = documentTemplate.createElement(prefix+delimeter+"Content")
                ;
        _SendRequestRequest.appendChild(_AttachmentContentList)
                .appendChild(_AttachmentContent)
                .appendChild(_Id);
        _AttachmentContent.appendChild(_Content);
        prefix = namespaces[0].getPrefix();
        Element _CallerInformationSystemSignature
                = documentTemplate.createElement(prefix+delimeter+"CallerInformationSystemSignature");
        _SendRequestRequest.appendChild(_CallerInformationSystemSignature);
    }

    private void fillDocument(){
        messageId = new MessageId();
        wsdl_1_1_requestTemplateDomGenerator();
        Element _MessageID
                = (Element) documentTemplate.getElementsByTagName(namespaces[0].getPrefix()+delimeter+"MessageID")
                    .item(0)
//                ,_SendRequestRequest
//                ,_SenderProvidedRequestData
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
        /*Document sourceXMLDoc = documentBuilder.parse("src/test1/Sample1.xml");*/
        DOMSource documentSource = new DOMSource(documentTemplate);
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
        documentRequest = (Document) documentResult.getNode();
    }

    @Override
    public ByteArrayOutputStream returnRequest(//int... operands
                                        ) {
        fillDocument();
        DOMImplementationLS domSaver = (DOMImplementationLS) DOMImpl;

        LSSerializer serializer = domSaver.createLSSerializer();
        LSOutput load_save_outer = domSaver.createLSOutput();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        load_save_outer.setByteStream(byteArrayOutputStream);
        serializer.write(documentRequest, load_save_outer);
        //serializer.write(documentTemplate, load_save_outer);

        System.out.println();
        return byteArrayOutputStream;
    }
}
