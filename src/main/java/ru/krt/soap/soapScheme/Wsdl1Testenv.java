package ru.krt.soap.soapScheme;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import ru.krt.soap.DocumentTemplatePhaseHolder;
import ru.krt.soap.plain.NamespacePrefix;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayOutputStream;

public class Wsdl1Testenv extends SoapScheme {

    public Wsdl1Testenv() {
        soapScheme_uri = "http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl";
        soapSchemeMnemonic = "testenv-1.1";
        addSoapScheme(soapSchemeMnemonic, soapScheme_uri);
    }

    private
    DocumentBuilderFactory documentBuilderFactory;
    private
    DocumentBuilder documentBuilder = null;
    private DOMImplementation DOMImpl= null;
    private Document documentStylesheet
            ,documentTemplate
            ,documentRequest
            ;

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

    private void DomGenerator(String ...prefixExternal){
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

        phaseHolder = new DocumentTemplatePhaseHolder();
        phaseHolder.createDocumentTemplateWithRootPrefix(soapenv.getNamespace(),soapenv.getPrefix()+delimeter+"Envelope", null);
        Element _Envelope = phaseHolder.getDocumentTemplate().getDocumentElement()
                ,_Header = phaseHolder.getDocumentTemplate().createElement(soapenv.getPrefix()+delimeter+"Header")
                ,_Body = phaseHolder.getDocumentTemplate().createElement(soapenv.getPrefix()+delimeter+"Body");
        _Envelope.setAttribute(qualifiedName+delimeter+namespaces[0].getPrefix(), namespaces[0].getNamespace());
        _Envelope.setAttribute(qualifiedName+delimeter+namespaces[1].getPrefix(), namespaces[1].getNamespace());
        _Envelope.appendChild(_Header);
        _Envelope.appendChild(_Body);
        prefix = namespaces[0].getPrefix();
        Element _SendRequestRequest
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"SendRequestRequest")
                ,_SenderProvidedRequestData
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"SendRequestRequestData")
                ,_MessageID
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"MessageID")
                ,_ReferenceMessageID
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"ReferenceMessageID")
                ,_TransactionCode
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"TransactionCode")
                ,_NodeID
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"NodeID")
                ,_EOL
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"EOL")
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
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"MessagePrimaryContent")
                ;
        _SenderProvidedRequestData.appendChild(_MessagePrimaryContent);
        prefix=namespaces[0].getPrefix();
        Element _PersonalSignature
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"PersonalSignature")
                ;
        _SenderProvidedRequestData.appendChild(_PersonalSignature);
        prefix=namespaces[1].getPrefix();
        Element _AttachmentHeaderList
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"AttachmentHeaderList")
                ,_AttachmentHeader
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"AttachmentHeader")
                ,_contentId
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"contentId")
                ,_MimeType
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"MimeType")
                ,_SignaturePKCS7
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"SignaturePKCS7");
        _SenderProvidedRequestData.appendChild(_AttachmentHeaderList)
                .appendChild(_AttachmentHeader)
                .appendChild(_contentId);
        _AttachmentHeader.appendChild(_MimeType);
        _AttachmentHeader.appendChild(_SignaturePKCS7);

        Element _RefAttachmentHeaderList
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"RefAttachmentHeaderList")
                ,_RefAttachmentHeader
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"RefAttachmentHeader")
                ,_uuid
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"RefAttachmentHeader")
                ,_Hash
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"RefAttachmentHeader")
                ,_MimeType_ref
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"RefAttachmentHeader")
                ,_SignaturePKCS7_ref
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"SignaturePKCS7")
                ;
        _SenderProvidedRequestData.appendChild(_RefAttachmentHeaderList)
                .appendChild(_RefAttachmentHeader)
                .appendChild(_uuid);
        _RefAttachmentHeader.appendChild(_Hash);
        _RefAttachmentHeader.appendChild(_MimeType_ref);
        _RefAttachmentHeader.appendChild(_SignaturePKCS7_ref);
        prefix = namespaces[0].getPrefix();
        Element _BusinessProcessMetadata
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"BusinessProcessMetadata")
                ,_TestMessage
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"TestMessage");
        _SenderProvidedRequestData.appendChild(_BusinessProcessMetadata);
        _SenderProvidedRequestData.appendChild(_TestMessage);
        prefix = namespaces[1].getPrefix();
        Element _AttachmentContentList
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"AttachmentContentList")
                ,_AttachmentContent
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"AttachmentContent")
                ,_Id
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"Id")
                ,_Content
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"Content")
                ;
        _SendRequestRequest.appendChild(_AttachmentContentList)
                .appendChild(_AttachmentContent)
                .appendChild(_Id);
        _AttachmentContent.appendChild(_Content);
        prefix = namespaces[0].getPrefix();
        Element _CallerInformationSystemSignature
                = phaseHolder.getDocumentTemplate().createElement(prefix+delimeter+"CallerInformationSystemSignature");
        _SendRequestRequest.appendChild(_CallerInformationSystemSignature);
    }


        @Override
    public Document returnRequestTemplate() {
        return null;
    }
    public Document getDocumentTemplate() {
        return documentTemplate;
    }
    public DOMImplementation getDOMImpl() {
        return DOMImpl;
    }

}
