package ru.krt.soap;

import ru.krt.soap.types.plain.DocumentDomImpl;

public class SoapRequestBulder {
    private DocumentDomImpl soapScheme = null;
    private String artefactPackage = null;
    private String artefactPrefix = null;
    private String artefactId = null;

    public SoapRequestBulder(DocumentDomImpl soapScheme, String artefactId) {
        this.soapScheme = soapScheme;
        this.artefactPackage = "artefactData";
        this.artefactPrefix = "ru.krt.soap.artefactData";
        this.artefactId = artefactId;
    }
}
