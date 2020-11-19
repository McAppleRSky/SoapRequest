package ru.krt.packageInvoker.type;

import ru.krt.soap.artefactData.AbstractArtefactData;

import java.util.Set;

public interface EnumeratedArtefactData {
/*
    String artefactData = "artefactData"
            //,artefactDataPackage = "ru.krt.soap."+artefactData
            ;
*/
    void enumArtefactData(String packageName, StringBuilder classNamesBuilder,
            Set< Class < ? extends AbstractArtefactData
            > > classes);
}
