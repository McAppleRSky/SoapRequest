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

import java.io.ByteArrayOutputStream;

public class VS00648001PFR001 extends ArtefactData {

    private DocumentBuilderFactory documentBuilderFactory;
    private DocumentBuilder documentBuilder;
    private DOMImplementation DOMImpl;
    private Document document;

    private MessageId messageId;

    public VS00648001PFR001() {
        namespace_uri = "http://kvs.pfr.com/snils-by-additionalData/1.0.1";
        id = "VS00648001PFR001";
        addArtefactData(id, namespace_uri);
        //result = new String[1];
    }

    private void fillDocument(){
        String prefix
                ,empty  = ""
                ,delimeter = ":"
                ,soapenv = "soapenv"
                ,nulled = "ns"
                ,firtst = "ns1"
                ,second = "ns2"
                ,third  = "ns3"
                ,fourth = "ns4"
                ;
        messageId = new MessageId();
        documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        DOMImpl = documentBuilder.getDOMImplementation();

        prefix = soapenv;
        document = DOMImpl.createDocument("urn"//"http://schemas.xmlsoap.org/soap/envelope/"
                , prefix+delimeter+"Envelope", null);
        //document = documentBuilder.newDocument();
        Element _Envelope = (Element) document.getFirstChild()
                ,_Header = document.createElement("Header")
                ,_Body = document.createElement("Body")
//                ,_Envelope //= document.getDocumentElement()
//                            = document.createElement("Envelope")
                //,_SendRequestRequest = document.createElementNS("urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1", "ns:SendRequestRequest")
                ,_SenderProvidedRequestData
                ,_MessageID
                ;

        _Envelope = document.getDocumentElement();
/*
        _Envelope.setAttribute("xmlns:ns", "urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1");
        _Envelope.setAttribute("xmlns:ns1", "urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1");
*/
        _Envelope.setPrefix(prefix);
        _Envelope.appendChild(_Header);
/*
        _SenderProvidedRequestData = document.createElement("ns:SenderProvidedRequestData");
        _SenderProvidedRequestData.setAttribute("Id", "SIGNED_BY_CALLER");
        _MessageID = document.createElement("ns:MessageID");
        _MessageID.setTextContent(messageId.generate());
*/
/*
        document.getDocumentElement()
                .setPrefix(prefix)

                .appendChild(_Envelope)
                .appendChild(_Header);
*/
/*
        document.getDocumentElement()
                .appendChild(_Body)
                //.appendChild(_SendRequestRequest)
                .appendChild(_SenderProvidedRequestData)
                .appendChild(_MessageID)
        ;
*/

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
