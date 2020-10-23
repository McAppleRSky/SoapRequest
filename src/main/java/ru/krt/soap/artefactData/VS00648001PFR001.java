package ru.krt.soap.artefactData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import ru.krt.soap.MessageId;
import ru.krt.soap.PlainNamespacePrefix;

import java.io.ByteArrayOutputStream;

public class VS00648001PFR001 extends ArtefactData {

    //private DocumentBuilderFactory documentBuilderFactory;
    //private DocumentBuilder documentBuilder;
    private DOMImplementation DOMImpl;
    private Document document;

    private MessageId messageId;

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
    PlainNamespacePrefix[] namespaces;
    private void  generateWsdl_1_1(String ...prefixExternal){
        PlainNamespacePrefix soapenv = new PlainNamespacePrefix("soapenv");
        soapenv.setNamespace("http://schemas.xmlsoap.org/soap/envelope/");
        //PlainNamespacePrefix[]
                namespaces = new PlainNamespacePrefix[prefixTip.length];
        for(int i=0;i<prefixTip.length;i++)
            namespaces[i]=new PlainNamespacePrefix(prefixTip[i]);
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
        DOMImpl = documentBuilder.getDOMImplementation();

        document = DOMImpl.createDocument(soapenv.getNamespace(),
                soapenv.getPrefix()+delimeter+"Envelope"
                , null);
        Element _Envelope = document.getDocumentElement()
                ,_Header = document.createElement(soapenv.getPrefix()+delimeter+"Header")
                ,_Body = document.createElement(soapenv.getPrefix()+delimeter+"Body");
        _Envelope.setAttribute(qualifiedName+delimeter+namespaces[0].getPrefix(), namespaces[0].getNamespace());
        _Envelope.setAttribute(qualifiedName+delimeter+namespaces[1].getPrefix(), namespaces[1].getNamespace());
        _Envelope.appendChild(_Header);
        _Envelope.appendChild(_Body);
        prefix = namespaces[0].getPrefix();
        Element _SendRequestRequest
                    = document.createElement(prefix+delimeter+"SendRequestRequest")
                ,_SenderProvidedRequestData
                    = document.createElement(prefix+delimeter+"SendRequestRequestData")
                ,_MessageID
                    = document.createElement(prefix+delimeter+"MessageID")
                ,_ReferenceMessageID
                    = document.createElement(prefix+delimeter+"ReferenceMessageID")
                ,_TransactionCode
                    = document.createElement(prefix+delimeter+"TransactionCode")
                ,_NodeID
                    = document.createElement(prefix+delimeter+"NodeID")
                ,_EOL
                    = document.createElement(prefix+delimeter+"EOL")
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
                = document.createElement(prefix+delimeter+"MessagePrimaryContent")
                ;
        _SenderProvidedRequestData.appendChild(_MessagePrimaryContent);
        prefix=namespaces[0].getPrefix();
        Element _PersonalSignature
                = document.createElement(prefix+delimeter+"PersonalSignature")
                ;
        _SenderProvidedRequestData.appendChild(_PersonalSignature);
        prefix=namespaces[1].getPrefix();
        Element _AttachmentHeaderList
                    = document.createElement(prefix+delimeter+"AttachmentHeaderList")
                ,_AttachmentHeader
                    = document.createElement(prefix+delimeter+"AttachmentHeader")
                ,_contentId
                    = document.createElement(prefix+delimeter+"contentId")
                ,_MimeType
                    = document.createElement(prefix+delimeter+"MimeType")
                ,_SignaturePKCS7
                    = document.createElement(prefix+delimeter+"SignaturePKCS7");
        _SenderProvidedRequestData.appendChild(_AttachmentHeaderList)
                .appendChild(_AttachmentHeader)
                .appendChild(_contentId);
        _AttachmentHeader.appendChild(_MimeType);
        _AttachmentHeader.appendChild(_SignaturePKCS7);

        Element _RefAttachmentHeaderList
                    = document.createElement(prefix+delimeter+"RefAttachmentHeaderList")
                ,_RefAttachmentHeader
                    = document.createElement(prefix+delimeter+"RefAttachmentHeader")
                ,_uuid
                    = document.createElement(prefix+delimeter+"RefAttachmentHeader")
                ,_Hash
                    = document.createElement(prefix+delimeter+"RefAttachmentHeader")
                ,_MimeType_ref
                    = document.createElement(prefix+delimeter+"RefAttachmentHeader")
                ,_SignaturePKCS7_ref
                    = document.createElement(prefix+delimeter+"SignaturePKCS7")
                ;
        _SenderProvidedRequestData.appendChild(_RefAttachmentHeaderList)
                .appendChild(_RefAttachmentHeader)
                .appendChild(_uuid);
        _RefAttachmentHeader.appendChild(_Hash);
        _RefAttachmentHeader.appendChild(_MimeType_ref);
        _RefAttachmentHeader.appendChild(_SignaturePKCS7_ref);
        prefix = namespaces[0].getPrefix();
        Element _BusinessProcessMetadata
                    = document.createElement(prefix+delimeter+"BusinessProcessMetadata")
                ,_TestMessage
                    = document.createElement(prefix+delimeter+"TestMessage");
        _SenderProvidedRequestData.appendChild(_BusinessProcessMetadata);
        _SenderProvidedRequestData.appendChild(_TestMessage);
        prefix = namespaces[1].getPrefix();
        Element _AttachmentContentList
                    = document.createElement(prefix+delimeter+"AttachmentContentList")
                ,_AttachmentContent
                    = document.createElement(prefix+delimeter+"AttachmentContent")
                ,_Id
                    = document.createElement(prefix+delimeter+"Id")
                ,_Content
                    = document.createElement(prefix+delimeter+"Content")
                ;
        _SendRequestRequest.appendChild(_AttachmentContentList)
                .appendChild(_AttachmentContent)
                .appendChild(_Id);
        _AttachmentContent.appendChild(_Content);
        prefix = namespaces[0].getPrefix();
        Element _CallerInformationSystemSignature
                = document.createElement(prefix+delimeter+"CallerInformationSystemSignature");
        _SendRequestRequest.appendChild(_CallerInformationSystemSignature);
    }

    private void fillDocument(){
        messageId = new MessageId();
        generateWsdl_1_1();
        Element _MessageID
                = (Element) document.getElementsByTagName(namespaces[0].getPrefix()+delimeter+"MessageID")
                    .item(0);
        _MessageID.setTextContent(messageId.generate());
    }

    @Override
    public ByteArrayOutputStream returnRequest(//int... operands
                                        ) {
        fillDocument();
        DOMImplementationLS domSaver = (DOMImplementationLS) DOMImpl;

        LSSerializer serializer = domSaver.createLSSerializer();
        LSOutput outer = domSaver.createLSOutput();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        outer.setByteStream(byteArrayOutputStream);
        serializer.write(document, outer);

        System.out.println();
        return byteArrayOutputStream;
    }
}
