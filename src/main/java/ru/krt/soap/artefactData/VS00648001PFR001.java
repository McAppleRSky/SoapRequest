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
        ImplDomDocument wsdlRequestTemplateImportSnilsPlain = wsdlRequestTemplate_importSnils( (ImplDomDocument)object[0] );
        //implDomDocumentToFile(wsdlRequestTemplateImportSnilsPlain,"wsdlRequestImportSnils.xml");
        ImplDomDocument wsdlRequestTemplateImportSnilsChengeNsPlain = wsdlRequestTemplateImportSnils_chengeNs(
                wsdlRequestTemplateImportSnilsPlain
        );
        //implDomDocumentToFile(wsdlRequestTemplateImportSnilsChengeNsPlain,"wsdlRequestImportSnilsChengeNs.xml");
        ImplDomDocument wsdlRequestTemplateImportSnilsChengeNsFaultsPlain = wsdlRequestTemplateImportSnilsChengeNs_chengeFaults(
                wsdlRequestTemplateImportSnilsChengeNsPlain
        );
        //implDomDocumentToFile(wsdlRequestTemplateImportSnilsChengeNsFaultsPlain,"wsdlRequestImportSnilsChengeNsFaults.xml");
        ImplDomDocument wsdlRequestTemplateImportSnilsChengeNsFaultsFillMsgIdPlain = wsdlRequestTemplateImportSnilsChengeNsFaults_fillMsgId(
                wsdlRequestTemplateImportSnilsChengeNsFaultsPlain
        );
        implDomDocumentToFile(wsdlRequestTemplateImportSnilsChengeNsFaultsPlain,"wsdlRequestImportSnilsChengeNsFaultsFillMsgId.xml");

        return null;
    }

    protected ImplDomDocument wsdlRequestTemplateImportSnilsChengeNsFaults_fillMsgId(ImplDomDocument wsdlRequestTemplateImportSnilsFillMsgIdPlain) {
        Document wsdlRequestTemplateImportSnilsChangeNsFaultsFillMsgId = wsdlRequestTemplateImportSnilsFillMsgIdPlain.getDocument();
        String prefix = ns+faults;
        Element _SenderProvidedRequestData=(Element)wsdlRequestTemplateImportSnilsChangeNsFaultsFillMsgId.getElementsByTagName(prefix+":"+tag[senderProvidedRequestData]).item(0)
                ,_MessageID=(Element)wsdlRequestTemplateImportSnilsChangeNsFaultsFillMsgId.getElementsByTagName(prefix+":"+tag[messageID]).item(0)
                ;
        MessageId messageId = new MessageId();
        _MessageID.setTextContent( messageId.generate() );
        _SenderProvidedRequestData.setAttribute("Id","SIGNED_BY_CALLER");

        return implDomDocumentFromBytes(
                implDomDocumentToBytes(
                        wsdlRequestTemplateImportSnilsFillMsgIdPlain.getDocument(),
                        wsdlRequestTemplateImportSnilsFillMsgIdPlain.getImplDom()
                )
        );
    }

    protected ImplDomDocument wsdlRequestTemplateImportSnils_chengeNs(ImplDomDocument wsdlRequestTemplateImportSnilsPlain) {
        Document wsdlRequestTemplateImportSnilsChengeNs = wsdlRequestTemplateImportSnilsPlain.getDocument();

        Element _SendRequestRequest=(Element)wsdlRequestTemplateImportSnilsChengeNs.getElementsByTagName(ns+":"+tag[sendRequestRequest]).item(0)
                ,_SenderProvidedRequestData=(Element)wsdlRequestTemplateImportSnilsChengeNs.getElementsByTagName(ns+":"+tag[senderProvidedRequestData]).item(0)
                ,_MessageID=(Element)wsdlRequestTemplateImportSnilsChengeNs.getElementsByTagName(ns+":"+tag[messageID]).item(0)
                ;
        String prefix = ns+faults;
            _SendRequestRequest.setPrefix(prefix);
            _SenderProvidedRequestData.setPrefix(prefix);
            _MessageID.setPrefix(prefix);

        return implDomDocumentFromBytes(
                implDomDocumentToBytes(
                        wsdlRequestTemplateImportSnilsPlain.getDocument(),
                        wsdlRequestTemplateImportSnilsPlain.getImplDom()
                ), false
        );
    }

    @Override
    public String getObjectId() {
        return objectId;
    }

    protected ImplDomDocument wsdlRequestTemplate_importSnils(ImplDomDocument wsdlTemplatePlain){
        ImplDomDocument //wsdlTemplate =null,
                requestSnilsSample = null;
        //wsdlTemplate = (ImplDomDocument)object[0];
        Document requestTemplateDocument = null, requestSnilsSampleDocument = null;
        requestTemplateDocument = wsdlTemplatePlain.getDocument();

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

        return implDomDocumentFromBytes(
                implDomDocumentToBytes(
                        wsdlTemplatePlain.getDocument(),
                        wsdlTemplatePlain.getImplDom()
                )
        );
    }

    protected ImplDomDocument wsdlRequestTemplateImportSnilsChengeNs_chengeFaults(ImplDomDocument wsdlRequestTemplateImportSnilsPlain) {
        Document wsdlRequestTemplateImportSnils=wsdlRequestTemplateImportSnilsPlain.getDocument();
        String prefix = ns+faults;
        Element _SendRequestRequest=(Element)wsdlRequestTemplateImportSnils.getElementsByTagName(prefix+":"+tag[sendRequestRequest]).item(0);
//        _SenderProvidedRequestData.setAttribute("Id","SIGNED_BY_CALLER");
        _SendRequestRequest.setAttribute("xmlns:"+prefix, nsUriUse[faults]);
/*
        try{
            _SendRequestRequest.setPrefix(prefix);
            _SenderProvidedRequestData.setPrefix(prefix);
            _MessageID.setPrefix(prefix);
        }catch (Exception exception){
            exception.printStackTrace();
        }
*/
        //.setAttributeNS("xmlns:"+ prefix, nsUriUse[faults],true);

//        _MessageID.setIdAttributeNS(nsUriUse[faults],true);
//        Attr _fault = wsdlRequestTemplateImportSnils.createAttributeNS(nsUriUse[faults], prefix);

//        _MessageID.setPrefix(prefix);
//        _SenderProvidedRequestData.setPrefix(prefix);
//        _SendRequestRequest.setPrefix(prefix);

//        _SendRequestRequest.removeAttribute("xmlns:ns2");
//        _SendRequestRequest.removeAttribute(//nsUriUse[type],
//                "xmlns:" +
//                                                                prefix);
//        _SendRequestRequest.setAttribute("xmlns:"+ prefix, nsUriUse[faults]);
//        MessageId messageId = new MessageId();
//        _MessageID.setTextContent( messageId.generate() );

        return implDomDocumentFromBytes(
                implDomDocumentToBytes(
                        wsdlRequestTemplateImportSnilsPlain.getDocument(),
                        wsdlRequestTemplateImportSnilsPlain.getImplDom()
                )
        );

    }


}
