package ru.krt.soap.soapScheme;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ru.krt.soap.types.plain.ImplDomDocument;

public class Wsdl1Testenv extends AbstractSoapScheme {

    public Wsdl1Testenv() {
        objectId = "http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl"
        ;
//        addObject(objectId[0]);
    }

    @Override
    public Object mainMethod(Object... argument) {
        ImplDomDocument wsdlRequestPlainTemplate = templateRequestFormEnvelopeSample();

        return templateRequestFormEnvelopeSample();
    }

    @Override
    public String getObjectId() {
        return objectId;
    }

    // https://www.jitendrazaa.com/blog/java/create-soap-message-using-java/
    // http://khpi-iip.mipk.kharkiv.edu/library/sotii/labs/lab_soap_create.html
    // http://khpi-iip.mipk.kharkiv.edu/library/extent/prog/iipXML/x-udom.html
    // http://java-online.ru/web-service-soap-client.xhtml
    // https://dev64.wordpress.com/2012/04/12/format-and-compare-xml-docs/
    // https://dev64.wordpress.com/2012/04/20/create-and-modify-xml-documents-using-java-dom/

    private String[] prefixTip = new String[]{
            "ns", "ns1", "ns2", "ns3", "ns4", "ns5", "ns6", "ns7", "ns8", "ns9"
    }
        ,nsUriTip = new String[]{
            //"http://schemas.xmlsoap.org/soap/envelope/",
            "urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1"
            ,"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1"
    }
        ,tag = new String[]{"Body"
            ,"SendRequestRequest"
            ,"SenderProvidedRequestData"
            ,"MessageID"
            ,"ReferenceMessageID"
            ,"TransactionCode"
            ,"NodeID"
            ,"EOL"
            ,"Id"
            ,"MessagePrimaryContent"
            ,"PersonalSignature"
            ,"AttachmentHeaderList"
            ,"AttachmentHeader"
            ,"contentId"
            ,"MimeType"
            ,"SignaturePKCS7"
            ,"RefAttachmentHeaderList"
            ,"RefAttachmentHeader"
            ,"uuid"
            ,"Hash"
            ,"BusinessProcessMetadata"
            ,"TestMessage"
            ,"AttachmentContentList"
            ,"AttachmentContent"
            ,"Content"
            ,"CallerInformationSystemSignature"
    };
    int body = 0
        ,sendRequestRequest = 1
        ,senderProvidedRequestData = 2
        ,messageID = 3
        ,referenceMessageID = 4
        ,transactionCode = 5
        ,nodeID = 6
        ,eol = 7
        ,id = 8
        ,messagePrimaryContent = 9
        ,personalSignature = 10
        ,attachmentHeaderList = 11
        ,attachmentHeader = 12
        ,contentId = 13
        ,mimeType = 14
        ,signaturePKCS7 = 15
        ,refAttachmentHeaderList = 16
        ,refAttachmentHeader = 17
        ,uuid = 18
        ,hash = 19
        ,businessProcessMetadata = 20
        ,testMessage = 21
        ,attachmentContentList = 22
        ,attachmentContent = 23
        ,content = 24
        ,callerInformationSystemSignature = 25;

    private String nsUriRoot = "http://schemas.xmlsoap.org/soap/envelope/"
            ,prefixRoot = "soapenv"
            ,xmlns = "xmlns"
            ,delimeter = ":"
            ,empty = ""
            ,question = "?"
            ,resourcesNameEnvelopeSample = "xml-samples/envelope.xml"
    ;
    protected ImplDomDocument templateRequestFormEnvelopeSample(String... prefixExternal) {
        for(int i=0;i<prefixExternal.length;i++)prefixTip[i] = prefixExternal[i];

        ImplDomDocument envelopeTemplate =
                implDomDocumentFromBytes(
                        bytesFromResources(resourcesNameEnvelopeSample)
        );
        DOMImplementation domImpl = envelopeTemplate.getImplDom();
        Document requestTemplate = envelopeTemplate.getDocument();
        if (domImpl == null) throw new NullPointerException("Nullable DOM implementation of Form Request");
        int nsi;
        Element _Body
                = (Element) requestTemplate.getElementsByTagNameNS(nsUriRoot,tag[body]).item(0)
                //requestTemplate.getDocumentElement();
                ;
        nsi = 0;
        Element _SendRequestRequest
                = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[sendRequestRequest] );
        Element _SenderProvidedRequestData
                = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[senderProvidedRequestData] );
        Element _MessageID
                = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[messageID] );
        _MessageID.setTextContent(question);
        Element _ReferenceMessageID
                = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[referenceMessageID]);
        _ReferenceMessageID.setTextContent(question);
        Element _TransactionCode
                = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[transactionCode] );
        _TransactionCode.setTextContent(question);
        Element _NodeID
                = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[nodeID] );
        _NodeID.setTextContent(question);
        Element _EOL = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[eol] );
        _EOL.setTextContent(question);
        _Body.appendChild(_SendRequestRequest)
                .appendChild(_SenderProvidedRequestData)
                .appendChild(_MessageID);
        _SenderProvidedRequestData.setAttribute("Id", question);
        _SenderProvidedRequestData.appendChild(_ReferenceMessageID);
        _SenderProvidedRequestData.appendChild(_TransactionCode);
        _SenderProvidedRequestData.appendChild(_NodeID);
        _SenderProvidedRequestData.appendChild(_EOL);
        _SenderProvidedRequestData.appendChild(_EOL);
        nsi=1;
        Element _MessagePrimaryContent
                = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[messagePrimaryContent] );
        _SenderProvidedRequestData.appendChild(_MessagePrimaryContent);
        nsi=0;
        Element _PersonalSignature
                = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[personalSignature] );
        _SenderProvidedRequestData.appendChild(_PersonalSignature);
        nsi=1;
        Element _AttachmentHeaderList
                = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[attachmentHeaderList] )
                , _AttachmentHeader
                    = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[attachmentHeader] )
                , _contentId
                    = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[contentId] )
                , _MimeType
                    = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[mimeType] )
                , _SignaturePKCS7
                    = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[signaturePKCS7] );
        _contentId.setTextContent(question);
        _MimeType.setTextContent(question);
        _SignaturePKCS7.setTextContent("cid:1202495970287");
        _AttachmentHeaderList.appendChild(_AttachmentHeader);
        _SenderProvidedRequestData.appendChild(_AttachmentHeaderList)
                .appendChild(_AttachmentHeader)
                .appendChild(_contentId);
        _AttachmentHeader.appendChild(_MimeType);
        _AttachmentHeader.appendChild(_SignaturePKCS7);

        Element _RefAttachmentHeaderList
                = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[refAttachmentHeaderList] )
                , _RefAttachmentHeader = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[refAttachmentHeader] )
                , _uuid = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[uuid] )
                , _Hash = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[hash] )
                , _MimeType_ref = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[mimeType] )
                , _SignaturePKCS7_ref = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[signaturePKCS7] )
                ;
        _uuid.setTextContent(question);
        _Hash.setTextContent(question);
        _MimeType_ref.setTextContent(question);
        _SignaturePKCS7_ref.setTextContent("cid:1048540585166");
        nsi = 0;
        Element _BusinessProcessMetadata
                = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[businessProcessMetadata] )
                , _TestMessage = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[testMessage] )
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
        nsi=1;
        Element _AttachmentContentList
                = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[attachmentContentList] )
                ,_AttachmentContent = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[attachmentContent] )
                ,_Id = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[id])
                , _Content = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[content] );
        _Id.setTextContent(question);
        _Content.setTextContent("cid:1527896676542");
        _SendRequestRequest.appendChild(_AttachmentContentList)
                .appendChild(_AttachmentContent)
                .appendChild(_Id);
        _AttachmentContent.appendChild(_Content);
        nsi=0;
        Element _CallerInformationSystemSignature
                = requestTemplate.createElement(
                prefixTip[nsi] + delimeter + tag[callerInformationSystemSignature] );
        _SendRequestRequest.appendChild(_CallerInformationSystemSignature);

        return implDomDocumentFromBytes(
                implDomDocumentToBytes(requestTemplate, domImpl)
        );
    }

}
