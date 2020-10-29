# Ручное подписывание запросов

## СМЭВ3 подпись

Веб интерфейс подписывания находится в классе ru.usb.smev3.transport.SignCamelRoute из пакета usb-smev3 .
Алиас, пароль и тело сообщения для подписи передаются пакету usb-sign

Подписывание выполняется POST запросом на адреса

```url
http://rsmev-smev3:8891/sign

http://rsmev-smev3-test:8891/sign
```

Пример тела сообщения:

```xml
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
  <SOAP-ENV:Body>
    <ns2:SendRequestRequest xmlns:ns2="urn://x-artefacts-smev-gov-ru/services/message-exchange/types/1.1" xmlns="urn://x-artefacts-smev-gov-ru/services/message-exchange/types/basic/1.1" xmlns:ns3="urn://x-artefacts-smev-gov-ru/services/message-exchange/types/faults/1.1">
      <ns2:SenderProvidedRequestData Id="SIGNED_BY_CALLER">
        <ns2:MessageID>9c6e9c70-18f9-11eb-8080-bb445a75084a</ns2:MessageID>
        <MessagePrimaryContent>
          <tns:SnilsByAdditionalDataRequest xmlns:tns="http://kvs.pfr.com/snils-by-additionalData/1.0.1" xmlns:pfr="http://common.kvs.pfr.com/1.0.0" xmlns:smev="urn://x-artefacts-smev-gov-ru/supplementary/commons/1.0.1">
            <smev:FamilyName>ИВАНОВ</smev:FamilyName>
            <smev:FirstName>ИВАН</smev:FirstName>
            <smev:Patronymic>ИВАНОВИЧ</smev:Patronymic>
            <tns:BirthDate>1967-05-21</tns:BirthDate>
            <tns:Gender>Male</tns:Gender>
            <tns:BirthPlace>
              <pfr:PlaceType>ОСОБОЕ</pfr:PlaceType>
              <pfr:Settlement>ЗАГОРСК</pfr:Settlement>
              <pfr:District>ЛЕНИНСКИЙ</pfr:District>
              <pfr:Region>МОСКОВСКАЯ ОБЛАСТЬ</pfr:Region>
              <pfr:Country>РФ</pfr:Country>
            </tns:BirthPlace>
            <smev:PassportRF>
              <smev:Series>0005</smev:Series>
              <smev:Number>777777</smev:Number>
              <smev:IssueDate>1986-06-13</smev:IssueDate>
              <smev:Issuer>ОВД</smev:Issuer>
            </smev:PassportRF>
          </tns:SnilsByAdditionalDataRequest>
        </MessagePrimaryContent>
        <ns2:BusinessProcessMetadata/>
        <ns2:TestMessage/>
      </ns2:SenderProvidedRequestData>
      <ns2:CallerInformationSystemSignature/>
    </ns2:SendRequestRequest>
  </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```
Основные моменты:
- в теле сообщения после SenderProvidedRequestData должен идти пустой блок CallerInformationSystemSignature, куда и будет записана подпись
- namespace важны, в данном примере - ns2:SenderProvidedRequestData и ns2:CallerInformationSystemSignature
- ответ использовать не меняя форматирование (SoapUI автоматически форматирует ответ, что не допустимо с уже подписаным сообщением). Ответ брать из http ответа (вкладка Raw в SoapUI)
