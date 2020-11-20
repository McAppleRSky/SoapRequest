package ru.krt.soap.artefactData;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import ru.krt.soap.MessageId;
import ru.krt.soap.types.plain.ImplDomDocument;

public class VS00648001PFR001 extends AbstractArtefactData {

    public VS00648001PFR001() {
        objectId = //new String[]{
                "http://kvs.pfr.com/snils-by-additionalData/1.0.1"
                //,"VS00648001PFR001"}
        ;
//        addObject(objectId[0]);
    }

    String resourceNameSnilsSample = "xml-artefact/VS00648001PFR001/Request0.xml";

    @Override
    public Object mainMethod(Object... object) {
        ImplDomDocument filledTemplateBySnilsSample = fillTemplateBySnilsSample( (ImplDomDocument)object[0] );
        Document doc = filledTemplateBySnilsSample.getDocument();
        //Element _MessageId = (Element)doc.getElementsByTagNameNS(nsUriUse[faults], "MessageId");
        MessageId messageId = new MessageId();
        //_MessageId.setTextContent( messageId.generate() );
        implDomDocumentToFile(
                new ImplDomDocument(
                        doc,
                        filledTemplateBySnilsSample.getImplDom()
                ),
                "fillRequest.xml"
        );
//        ImplDomDocument docPlain =     implDomDocumentToFile(,"")

        return null;
    }

    @Override
    public String getObjectId() {
        return objectId;
    }

    public ImplDomDocument fillTemplateBySnilsSample(ImplDomDocument wsdlTemplate){
        ImplDomDocument //wsdlTemplate =null,
                requestSnilsSample = null;
        //wsdlTemplate = (ImplDomDocument)object[0];
        Document requestTemplateDocument = null, requestSnilsSampleDocument = null;
        requestTemplateDocument = wsdlTemplate.getDocument();

        requestSnilsSample =  implDomDocumentFromBytes(
                bytesFromResources(resourceNameSnilsSample)
        );
        requestSnilsSampleDocument = requestSnilsSample.getDocument();

        Node _SnilsByAdditionalDataRequest = requestSnilsSampleDocument.getElementsByTagNameNS(objectId,
                "SnilsByAdditionalDataRequest").item(0);
        Node importedNode_SnilsByAdditionalDataRequest = requestTemplateDocument.importNode(
                _SnilsByAdditionalDataRequest
                ,true);

        Element _MessagePrimaryContent = (Element)requestTemplateDocument.getElementsByTagNameNS(
                    nsUriUse[basic], tag[messagePrimaryContent]).item(0)
                //,_SnilsByAdditionalDataRequest = (Element)requestSnilsSampledocument.getElementsByTagNameNS(objectId,"SnilsByAdditionalDataRequest").item(0)
        ;
        _MessagePrimaryContent.appendChild(importedNode_SnilsByAdditionalDataRequest);

        Element _SendRequestRequest=(Element)requestTemplateDocument.getElementsByTagNameNS(nsUriUse[0], tag[sendRequestRequest]).item(0)
                ,_SenderProvidedRequestData=(Element)requestTemplateDocument.getElementsByTagNameNS(nsUriUse[0], tag[senderProvidedRequestData]).item(0)
                ,_MessageID=(Element)requestTemplateDocument.getElementsByTagNameNS(nsUriUse[0], tag[messageID]).item(0)
                ;
        String prefix = ns+faults;
        _SenderProvidedRequestData.setAttribute("Id","SIGNED_BY_CALLER");
//        _MessageID.setIdAttributeNS(nsUriUse[faults],true);
        Attr _fault = requestTemplateDocument.createAttributeNS(nsUriUse[faults], prefix);

//        _MessageID.setPrefix(prefix);
//        _SenderProvidedRequestData.setPrefix(prefix);
//        _SendRequestRequest.setPrefix(prefix);

        _SendRequestRequest.removeAttribute("xmlns:ns2");
        //_SendRequestRequest.setIdAttributeNS(nsUriUse[faults],"xmlns:"+prefix,true); //.setAttributeNS("xmlns:"+ prefix, nsUriUse[faults],true);
//        _SendRequestRequest.removeAttribute(//nsUriUse[type],
//                "xmlns:" +
//                                                                prefix);
//        _SendRequestRequest.setAttribute("xmlns:"+ prefix, nsUriUse[faults]);
//        MessageId messageId = new MessageId();
//        _MessageID.setTextContent( messageId.generate() );

        return implDomDocumentFromBytes(
                implDomDocumentToBytes(wsdlTemplate.getDocument(), wsdlTemplate.getImplDom())
        );
    }

}
