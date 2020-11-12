package ru.krt.soap.soapScheme;

import ru.krt.soap.types.plain.DocumentDomimpl;

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
    public DocumentDomimpl mainMethod(Object... argument) {
        return new DocumentDomimpl(null, null);
    }
    @Override
    public String getObjectId() {
        return objectId;
    }

}
