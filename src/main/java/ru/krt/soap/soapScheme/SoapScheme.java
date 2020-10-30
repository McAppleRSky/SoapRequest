package ru.krt.soap.soapScheme;

import ru.krt.packageInvoker.PackageEnumerator;

import java.util.ArrayList;

public abstract class SoapScheme extends PackageEnumerator {

    public SoapScheme() {
        packageObjects = new ArrayList<>();
    }

}
