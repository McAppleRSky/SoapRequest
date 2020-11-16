package ru.krt.soap.soapScheme;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.krt.soap.types.plain.DocumentDomImpl;
import ru.krt.soap.types.plain.NamespacePrefix;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Wsdl1Testenv extends AbstractSoapScheme {

    // https://www.jitendrazaa.com/blog/java/create-soap-message-using-java/
    // http://khpi-iip.mipk.kharkiv.edu/library/sotii/labs/lab_soap_create.html
    // http://java-online.ru/web-service-soap-client.xhtml
    // https://dev64.wordpress.com/2012/04/12/format-and-compare-xml-docs/
    // https://dev64.wordpress.com/2012/04/20/create-and-modify-xml-documents-using-java-dom/

    public Wsdl1Testenv() {
        objectId = "http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl"
        ;
//        addObject(objectId[0]);
    }

    private String[] prefixTip = new String[]{
            "soapenv", "ns", "ns1", "ns2", "ns3", "ns4", "ns5", "ns6", "ns7", "ns8", "ns9"
    }, nsUriTip = new String[]{
            "http://schemas.xmlsoap.org/soap/envelope/"
            ,"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1"
            ,"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1"
    };
    private String xmlns = "xmlns"
            ,delimeter = ":"
            ,empty = ""
            ,question = "?"
    ;
    private DocumentDomImpl generateTemplateFormRequest(String... prefixExternal) {
        for(int i=0;i<prefixExternal.length;i++)prefixTip[i] = prefixExternal[i];
        DocumentBuilderFactory documentBuilderFactory = null;
        DocumentBuilder documentBuilder = null;
        DOMImplementation domImpl = null;
        Document document = null;
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        domImpl = documentBuilder.getDOMImplementation();
        if (domImpl == null) throw new NullPointerException("Nullable DOM implementation of Form Request");
        int nsi = 0;
        String  rootElementName = "Envelope"
                ,namespaceURI = nsUriTip[nsi]
                ,qualifiedName = prefixTip[nsi] + delimeter + rootElementName
                ;
        document = domImpl.createDocument(namespaceURI, qualifiedName, null);
        if (document == null) throw new NullPointerException("Nullable document of Form Request");

        Element _Envelope = (Element) document.getElementsByTagNameNS(namespaceURI, rootElementName).item(0)
                ,_Header = document.createElement(prefixTip[nsi]+ delimeter + "Header")
                ,_Body = document.createElement(prefixTip[nsi]+ delimeter + "Body")
                ;
        nsi = 1;
        _Envelope.setAttribute(xmlns + delimeter + prefixTip[nsi], nsUriTip[nsi]);
        nsi = 2;
        _Envelope.setAttribute(xmlns + delimeter + prefixTip[nsi], nsUriTip[nsi]);
        _Envelope.appendChild(_Header);
        _Envelope.appendChild(_Body);
        nsi = 1;
        Element _SendRequestRequest
                = document.createElement(prefixTip[nsi] + delimeter + "SendRequestRequest");
        Element _SenderProvidedRequestData
                        = document.createElement(prefixTip[nsi] + delimeter + "SenderProvidedRequestData");
        Element _MessageID
                        = document.createElement(prefixTip[nsi] + delimeter + "MessageID");
        _MessageID.setTextContent(question);
        Element _ReferenceMessageID
                        = document.createElement(prefixTip[nsi] + delimeter + "ReferenceMessageID");
        _ReferenceMessageID.setTextContent(question);
        Element _TransactionCode
                        = document.createElement(prefixTip[nsi] + delimeter + "TransactionCode");
        _TransactionCode.setTextContent(question);
        Element _NodeID
                        = document.createElement(prefixTip[nsi] + delimeter + "NodeID");
        _NodeID.setTextContent(question);
        Element _EOL
                        = document.createElement(prefixTip[nsi] + delimeter + "EOL");
        _EOL.setTextContent(question);
        _Body.appendChild(_SendRequestRequest)
                .appendChild(_SenderProvidedRequestData)
                .appendChild(_MessageID);
        _SenderProvidedRequestData.setAttribute("Id", "?");
        _SenderProvidedRequestData.appendChild(_ReferenceMessageID);
        _SenderProvidedRequestData.appendChild(_TransactionCode);
        _SenderProvidedRequestData.appendChild(_NodeID);
        _SenderProvidedRequestData.appendChild(_EOL);
        _SenderProvidedRequestData.appendChild(_EOL);
        nsi=2;
        Element _MessagePrimaryContent
                = document.createElement(prefixTip[nsi] + delimeter + "MessagePrimaryContent");
        _SenderProvidedRequestData.appendChild(_MessagePrimaryContent);
        nsi=1;
        Element _PersonalSignature
                = document.createElement(prefixTip[nsi] + delimeter + "PersonalSignature");
        _SenderProvidedRequestData.appendChild(_PersonalSignature);
        nsi=2;
        Element _AttachmentHeaderList
                    = document.createElement(prefixTip[nsi] + delimeter + "AttachmentHeaderList")
                , _AttachmentHeader
                    = document.createElement(prefixTip[nsi] + delimeter + "AttachmentHeader")
                , _contentId
                    = document.createElement(prefixTip[nsi] + delimeter + "contentId")
                , _MimeType
                    = document.createElement(prefixTip[nsi] + delimeter + "MimeType")
                , _SignaturePKCS7
                    = document.createElement(prefixTip[nsi] + delimeter + "SignaturePKCS7");
        _contentId.setTextContent(question);
        _MimeType.setTextContent(question);
        _SignaturePKCS7.setTextContent("cid:1202495970287");
        _AttachmentHeaderList.appendChild(_AttachmentHeader);
        _SenderProvidedRequestData.appendChild(_AttachmentHeaderList)
                .appendChild(_AttachmentHeader)
                .appendChild(_contentId);
        _AttachmentHeader.appendChild(_MimeType);
        _AttachmentHeader.appendChild(_SignaturePKCS7);

        Element _RefAttachmentHeaderList = document.createElement(prefixTip[nsi] + delimeter + "RefAttachmentHeaderList")
                , _RefAttachmentHeader = document.createElement(prefixTip[nsi] + delimeter + "RefAttachmentHeader")
                , _uuid = document.createElement(prefixTip[nsi] + delimeter + "uuid")
                , _Hash = document.createElement(prefixTip[nsi] + delimeter + "Hash")
                , _MimeType_ref = document.createElement(prefixTip[nsi] + delimeter + "MimeType")
                , _SignaturePKCS7_ref = document.createElement(prefixTip[nsi] + delimeter + "SignaturePKCS7")
                ;
        _uuid.setTextContent(question);
        _Hash.setTextContent(question);
        _MimeType_ref.setTextContent(question);
        _SignaturePKCS7_ref.setTextContent("cid:1048540585166");
        nsi = 1;
        Element _BusinessProcessMetadata
                    = document.createElement(prefixTip[nsi] + delimeter + "BusinessProcessMetadata")
                , _TestMessage
                    = document.createElement(prefixTip[nsi] + delimeter + "TestMessage")
                ;
        _RefAttachmentHeader
                .appendChild(_uuid)
                .appendChild(_Hash)
                .appendChild(_MimeType_ref)
                .appendChild(_SignaturePKCS7_ref);
        _RefAttachmentHeaderList
                .appendChild(_RefAttachmentHeader)
                .appendChild(_uuid);
        _RefAttachmentHeader.appendChild(_Hash);
        _RefAttachmentHeader.appendChild(_MimeType_ref);
        _RefAttachmentHeader.appendChild(_SignaturePKCS7_ref);
        _SenderProvidedRequestData.appendChild(_RefAttachmentHeaderList);
        _SenderProvidedRequestData.appendChild(_BusinessProcessMetadata);
        _SenderProvidedRequestData.appendChild(_TestMessage);
        nsi=2;
        Element _AttachmentContentList
                = document.createElement(prefixTip[nsi] + delimeter + "AttachmentContentList")
                , _AttachmentContent
                    = document.createElement(prefixTip[nsi] + delimeter + "AttachmentContent")
                , _Id
                    = document.createElement(prefixTip[nsi] + delimeter + "Id")
                , _Content
                    = document.createElement(prefixTip[nsi] + delimeter + "Content");
        _Id.setTextContent(question);
        _Content.setTextContent("cid:1527896676542");
        _SendRequestRequest.appendChild(_AttachmentContentList)
                .appendChild(_AttachmentContent)
                .appendChild(_Id);
        _AttachmentContent.appendChild(_Content);
        nsi=1;
        Element _CallerInformationSystemSignature
                = document.createElement(prefixTip[nsi] + delimeter + "CallerInformationSystemSignature");
        _SendRequestRequest.appendChild(_CallerInformationSystemSignature);
        return new DocumentDomImpl(domImpl, document);
    }

    @Override
    public Object mainMethod(Object... argument) {
        return generateTemplateFormRequest();
    }

    @Override
    public String getObjectId() {
        return objectId;
    }
}
