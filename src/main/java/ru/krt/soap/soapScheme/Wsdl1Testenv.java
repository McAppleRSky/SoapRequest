package ru.krt.soap.soapScheme;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
//import ru.krt.soap.DocumentTemplatePhaseHolder;
import ru.krt.packageInvoker.PackageEnumerator;
import ru.krt.soap.plainTypes.DocumentDomimpl;
import ru.krt.soap.plainTypes.NamespacePrefix;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Wsdl1Testenv extends SoapScheme {

    public Wsdl1Testenv() {
        objectId = new String[]{
                //soapScheme_uri
                "http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl",
                //soapSchemeMnemonic
                "testenv-1.1"
        };
        addObject(objectId[0]);
    }

    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder = null;
    private DOMImplementation DOMImpl = null;
    private Document documentTemplate
            ,documentStylesheet
            //,documentRequest
            ;

    private final String[] prefixTip = new String[]{
            "ns", "ns1", "ns2", "ns3", "ns4", "ns5", "ns6", "ns7", "ns8", "ns9"
    }, nsTip = new String[]{
            "urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1"
            , "urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1"
    };

    private String prefix, empty = "", delimeter = ":", qualifiedName = "xmlns";

    NamespacePrefix[] namespaces;

    private void DomGenerator(String... prefixExternal) {
        NamespacePrefix soapenv = new NamespacePrefix("soapenv");
        soapenv.setNamespace("http://schemas.xmlsoap.org/soap/envelope/");
        //NamespacePrefix[]
        namespaces = new NamespacePrefix[prefixTip.length];
        for (int i = 0; i < prefixTip.length; i++)
            namespaces[i] = new NamespacePrefix(prefixTip[i]);
        for (int i = 0; i < prefixExternal.length; i++)
            namespaces[i].setPrefix(prefixExternal[i]);
        for (int i = 0; i < nsTip.length; i++)
            namespaces[i].setNamespace(nsTip[i]);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        String namespaceURI = soapenv.getNamespace(),
                qualifiedName = soapenv.getPrefix() + delimeter + "Envelope";
        documentTemplate = DOMImpl.createDocument(namespaceURI, qualifiedName, null);
        Element _Envelope = documentTemplate.getDocumentElement(),
                _Header = documentTemplate.createElement(soapenv.getPrefix() + delimeter + "Header"),
                _Body = documentTemplate.createElement(soapenv.getPrefix() + delimeter + "Body");
        _Envelope.setAttribute(qualifiedName + delimeter + namespaces[0].getPrefix(), namespaces[0].getNamespace());
        _Envelope.setAttribute(qualifiedName + delimeter + namespaces[1].getPrefix(), namespaces[1].getNamespace());
        _Envelope.appendChild(_Header);
        _Envelope.appendChild(_Body);
        prefix = namespaces[0].getPrefix();
        Element _SendRequestRequest
                = documentTemplate.createElement(prefix + delimeter + "SendRequestRequest");
        Element _SenderProvidedRequestData
                        = documentTemplate.createElement(prefix + delimeter + "SendRequestRequestData");
        Element _MessageID
                        = documentTemplate.createElement(prefix + delimeter + "MessageID");
        Element _ReferenceMessageID
                        = documentTemplate.createElement(prefix + delimeter + "ReferenceMessageID");
        Element _TransactionCode
                        = documentTemplate.createElement(prefix + delimeter + "TransactionCode");
        Element _NodeID
                        = documentTemplate.createElement(prefix + delimeter + "NodeID");
        Element _EOL
                        = documentTemplate.createElement(prefix + delimeter + "EOL");
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
                = documentTemplate.createElement(prefix + delimeter + "MessagePrimaryContent");
        _SenderProvidedRequestData.appendChild(_MessagePrimaryContent);
        prefix = namespaces[0].getPrefix();
        Element _PersonalSignature
                = documentTemplate.createElement(prefix + delimeter + "PersonalSignature");
        _SenderProvidedRequestData.appendChild(_PersonalSignature);
        prefix = namespaces[1].getPrefix();
        Element _AttachmentHeaderList
                = documentTemplate.createElement(prefix + delimeter + "AttachmentHeaderList"), _AttachmentHeader
                = documentTemplate.createElement(prefix + delimeter + "AttachmentHeader"), _contentId
                = documentTemplate.createElement(prefix + delimeter + "contentId"), _MimeType
                = documentTemplate.createElement(prefix + delimeter + "MimeType"), _SignaturePKCS7
                = documentTemplate.createElement(prefix + delimeter + "SignaturePKCS7");
        _SenderProvidedRequestData.appendChild(_AttachmentHeaderList)
                .appendChild(_AttachmentHeader)
                .appendChild(_contentId);
        _AttachmentHeader.appendChild(_MimeType);
        _AttachmentHeader.appendChild(_SignaturePKCS7);
        Element _RefAttachmentHeaderList
                = documentTemplate.createElement(prefix + delimeter + "RefAttachmentHeaderList"), _RefAttachmentHeader
                = documentTemplate.createElement(prefix + delimeter + "RefAttachmentHeader"), _uuid
                = documentTemplate.createElement(prefix + delimeter + "RefAttachmentHeader"), _Hash
                = documentTemplate.createElement(prefix + delimeter + "RefAttachmentHeader"), _MimeType_ref
                = documentTemplate.createElement(prefix + delimeter + "RefAttachmentHeader"), _SignaturePKCS7_ref
                = documentTemplate.createElement(prefix + delimeter + "SignaturePKCS7");
        _SenderProvidedRequestData.appendChild(_RefAttachmentHeaderList)
                .appendChild(_RefAttachmentHeader)
                .appendChild(_uuid);
        _RefAttachmentHeader.appendChild(_Hash);
        _RefAttachmentHeader.appendChild(_MimeType_ref);
        _RefAttachmentHeader.appendChild(_SignaturePKCS7_ref);
        prefix = namespaces[0].getPrefix();
        Element _BusinessProcessMetadata
                = documentTemplate.createElement(prefix + delimeter + "BusinessProcessMetadata"), _TestMessage
                = documentTemplate.createElement(prefix + delimeter + "TestMessage");
        _SenderProvidedRequestData.appendChild(_BusinessProcessMetadata);
        _SenderProvidedRequestData.appendChild(_TestMessage);
        prefix = namespaces[1].getPrefix();
        Element _AttachmentContentList
                = documentTemplate.createElement(prefix + delimeter + "AttachmentContentList"), _AttachmentContent
                = documentTemplate.createElement(prefix + delimeter + "AttachmentContent"), _Id
                = documentTemplate.createElement(prefix + delimeter + "Id"), _Content
                = documentTemplate.createElement(prefix + delimeter + "Content");
        _SendRequestRequest.appendChild(_AttachmentContentList)
                .appendChild(_AttachmentContent)
                .appendChild(_Id);
        _AttachmentContent.appendChild(_Content);
        prefix = namespaces[0].getPrefix();
        Element _CallerInformationSystemSignature
                = documentTemplate.createElement(prefix + delimeter + "CallerInformationSystemSignature");
        _SendRequestRequest.appendChild(_CallerInformationSystemSignature);
    }

    @Override
    public DocumentDomimpl mainMethod() {
        DomGenerator();
        return new DocumentDomimpl(DOMImpl, documentTemplate);
    }

}
