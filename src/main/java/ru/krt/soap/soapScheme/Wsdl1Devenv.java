package ru.krt.soap.soapScheme;

import ru.krt.soap.types.plain.ImplDomDocument;

public class Wsdl1Devenv extends AbstractSoapScheme {

    public Wsdl1Devenv() {
        objectId = //new String[]{
                //soapScheme_uri
                "http://smev3-d.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl"
                //soapSchemeMnemonic
//                ,"devenv-1.1"}
        ;
//        addObject(objectId[0]);
    }

    @Override
    public ImplDomDocument mainMethod(Object... argument) {
        return new ImplDomDocument(null, null);
    }
    @Override
    public String getObjectId() {
        return objectId;
    }

}
