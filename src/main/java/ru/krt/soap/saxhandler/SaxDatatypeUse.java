package ru.krt.soap.saxhandler;

public interface SaxDatatypeUse {
    String ns = "ns";
    String[] nsUriUse = new String[]{
            //"http://schemas.xmlsoap.org/soap/envelope/",
            "urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1"
            ,"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1"
            ,"urn://x-artefacts-smev-gov-ru/services/message-exchange/types/faults/1.1"
    }, tag = new String[]{"Body"
            , "SendRequestRequest"
            , "SenderProvidedRequestData"
            , "MessageID"
            , "ReferenceMessageID"
            , "TransactionCode"
            , "NodeID"
            , "EOL"
            , "Id"
            , "MessagePrimaryContent"
            , "PersonalSignature"
            , "AttachmentHeaderList"
            , "AttachmentHeader"
            , "contentId"
            , "MimeType"
            , "SignaturePKCS7"
            , "RefAttachmentHeaderList"
            , "RefAttachmentHeader"
            , "uuid"
            , "Hash"
            , "BusinessProcessMetadata"
            , "TestMessage"
            , "AttachmentContentList"
            , "AttachmentContent"
            , "Content"
            , "CallerInformationSystemSignature"

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
            ,callerInformationSystemSignature = 25

            ,type = 0, basic=1, faults=2;

}
