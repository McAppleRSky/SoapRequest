package ru.krt.soap.soapScheme;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.krt.soap.types.plain.DocumentDomimpl;
import ru.krt.soap.types.plain.NamespacePrefix;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Wsdl1Testenv extends AbstractSoapScheme {

    public Wsdl1Testenv() {
        objectId = //new String[]{
                "http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl"
//                ,"testenv-1.1"}
        ;
//        addObject(objectId[0]);
    }

    private final String[] prefixTip = new String[]{
            "ns", "ns1", "ns2", "ns3", "ns4", "ns5", "ns6", "ns7", "ns8", "ns9"
    }, nsTip = new String[]{
            "urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1"
            ,"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1"
    };

    private String prefix, empty = "", delimeter = ":", qualifiedName = "xmlns";

    NamespacePrefix[] namespaces;

    private DocumentDomimpl generateTemplateFormRequest(String... prefixExternal) {
        NamespacePrefix soapenv = new NamespacePrefix("soapenv");
        soapenv.setNamespace("http://schemas.xmlsoap.org/soap/envelope/");
        namespaces = new NamespacePrefix[prefixTip.length];
        for (int i = 0; i < prefixTip.length; i++)
            namespaces[i] = new NamespacePrefix(prefixTip[i]);
        for (int i = 0; i < prefixExternal.length; i++)
            namespaces[i].setPrefix(prefixExternal[i]);
        for (int i = 0; i < nsTip.length; i++)
            namespaces[i].setNamespace(nsTip[i]);

        //private
        DocumentBuilderFactory documentBuilderFactory = null;
        //private
        DocumentBuilder documentBuilder = null;
        //private
        DOMImplementation domImpl = null;
        //private
        Document documentTemplateFormRequest = null
                //,documentStylesheet
                //,documentRequest
                ;

        //DocumentBuilderFactory
                documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        //DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        domImpl = documentBuilder.getDOMImplementation();
        if (domImpl == null) throw new NullPointerException("Nullable DOM implementation of Form Request");
        String namespaceURI = soapenv.getNamespace(),
                qualifiedName = soapenv.getPrefix() + delimeter + "Envelope";
        documentTemplateFormRequest = domImpl.createDocument(namespaceURI, qualifiedName, null);
        if (documentTemplateFormRequest == null) throw new NullPointerException("Nullable document of Form Request");
        Element _Envelope = documentTemplateFormRequest.getDocumentElement(),
                _Header = documentTemplateFormRequest.createElement(soapenv.getPrefix() + delimeter + "Header"),
                _Body = documentTemplateFormRequest.createElement(soapenv.getPrefix() + delimeter + "Body");
        _Envelope.setAttribute(qualifiedName + delimeter + namespaces[0].getPrefix(), namespaces[0].getNamespace());
        _Envelope.setAttribute(qualifiedName + delimeter + namespaces[1].getPrefix(), namespaces[1].getNamespace());
        _Envelope.appendChild(_Header);
        _Envelope.appendChild(_Body);
        prefix = namespaces[0].getPrefix();
        Element _SendRequestRequest
                = documentTemplateFormRequest.createElement(prefix + delimeter + "SendRequestRequest");
        Element _SenderProvidedRequestData
                        = documentTemplateFormRequest.createElement(prefix + delimeter + "SendRequestRequestData");
        Element _MessageID
                        = documentTemplateFormRequest.createElement(prefix + delimeter + "MessageID");
        Element _ReferenceMessageID
                        = documentTemplateFormRequest.createElement(prefix + delimeter + "ReferenceMessageID");
        Element _TransactionCode
                        = documentTemplateFormRequest.createElement(prefix + delimeter + "TransactionCode");
        Element _NodeID
                        = documentTemplateFormRequest.createElement(prefix + delimeter + "NodeID");
        Element _EOL
                        = documentTemplateFormRequest.createElement(prefix + delimeter + "EOL");
        _Body.appendChild(_SendRequestRequest)
                .appendChild(_SenderProvidedRequestData)
                .appendChild(_MessageID);
        _SenderProvidedRequestData.setAttribute("Id", empty);
        _SenderProvidedRequestData.appendChild(_ReferenceMessageID);
        _SenderProvidedRequestData.appendChild(_TransactionCode);
        _SenderProvidedRequestData.appendChild(_NodeID);
        _SenderProvidedRequestData.appendChild(_EOL);
        _SenderProvidedRequestData.appendChild(_EOL);
        prefix = namespaces[1].getPrefix();
        Element _MessagePrimaryContent
                = documentTemplateFormRequest.createElement(prefix + delimeter + "MessagePrimaryContent");
        _SenderProvidedRequestData.appendChild(_MessagePrimaryContent);
        prefix = namespaces[0].getPrefix();
        Element _PersonalSignature
                = documentTemplateFormRequest.createElement(prefix + delimeter + "PersonalSignature");
        _SenderProvidedRequestData.appendChild(_PersonalSignature);
        prefix = namespaces[1].getPrefix();
        Element _AttachmentHeaderList
                = documentTemplateFormRequest.createElement(prefix + delimeter + "AttachmentHeaderList"), _AttachmentHeader
                = documentTemplateFormRequest.createElement(prefix + delimeter + "AttachmentHeader"), _contentId
                = documentTemplateFormRequest.createElement(prefix + delimeter + "contentId"), _MimeType
                = documentTemplateFormRequest.createElement(prefix + delimeter + "MimeType"), _SignaturePKCS7
                = documentTemplateFormRequest.createElement(prefix + delimeter + "SignaturePKCS7");
        _SenderProvidedRequestData.appendChild(_AttachmentHeaderList)
                .appendChild(_AttachmentHeader)
                .appendChild(_contentId);
        _AttachmentHeader.appendChild(_MimeType);
        _AttachmentHeader.appendChild(_SignaturePKCS7);
        Element _RefAttachmentHeaderList
                = documentTemplateFormRequest.createElement(prefix + delimeter + "RefAttachmentHeaderList"), _RefAttachmentHeader
                = documentTemplateFormRequest.createElement(prefix + delimeter + "RefAttachmentHeader"), _uuid
                = documentTemplateFormRequest.createElement(prefix + delimeter + "RefAttachmentHeader"), _Hash
                = documentTemplateFormRequest.createElement(prefix + delimeter + "RefAttachmentHeader"), _MimeType_ref
                = documentTemplateFormRequest.createElement(prefix + delimeter + "RefAttachmentHeader"), _SignaturePKCS7_ref
                = documentTemplateFormRequest.createElement(prefix + delimeter + "SignaturePKCS7");
        _SenderProvidedRequestData.appendChild(_RefAttachmentHeaderList)
                .appendChild(_RefAttachmentHeader)
                .appendChild(_uuid);
        _RefAttachmentHeader.appendChild(_Hash);
        _RefAttachmentHeader.appendChild(_MimeType_ref);
        _RefAttachmentHeader.appendChild(_SignaturePKCS7_ref);
        prefix = namespaces[0].getPrefix();
        Element _BusinessProcessMetadata
                = documentTemplateFormRequest.createElement(prefix + delimeter + "BusinessProcessMetadata"), _TestMessage
                = documentTemplateFormRequest.createElement(prefix + delimeter + "TestMessage");
        _SenderProvidedRequestData.appendChild(_BusinessProcessMetadata);
        _SenderProvidedRequestData.appendChild(_TestMessage);
        prefix = namespaces[1].getPrefix();
        Element _AttachmentContentList
                = documentTemplateFormRequest.createElement(prefix + delimeter + "AttachmentContentList"), _AttachmentContent
                = documentTemplateFormRequest.createElement(prefix + delimeter + "AttachmentContent"), _Id
                = documentTemplateFormRequest.createElement(prefix + delimeter + "Id"), _Content
                = documentTemplateFormRequest.createElement(prefix + delimeter + "Content");
        _SendRequestRequest.appendChild(_AttachmentContentList)
                .appendChild(_AttachmentContent)
                .appendChild(_Id);
        _AttachmentContent.appendChild(_Content);
        prefix = namespaces[0].getPrefix();
        Element _CallerInformationSystemSignature
                = documentTemplateFormRequest.createElement(prefix + delimeter + "CallerInformationSystemSignature");
        _SendRequestRequest.appendChild(_CallerInformationSystemSignature);
        return new DocumentDomimpl(domImpl, documentTemplateFormRequest);
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
