package ru.krt.packageInvoker.type;

import ru.krt.soap.soapScheme.AbstractSoapScheme;

import java.util.Set;

public interface EnumeratedSoapScheme {
/*
    String soapScheme = "soapScheme"
            //,soapSchemePackage = "ru.krt.soap." + soapScheme
            ;
*/
    void enumSoapScheme(String packageName, StringBuilder classNamesBuilder,
            Set< Class < ? extends AbstractSoapScheme
            > > classes);
}
