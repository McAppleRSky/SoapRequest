package ru.krt.soap;

import org.reflections.Reflections;
import ru.krt.packageInvoker.PackageInvoker;
import ru.krt.soap.artefactData.AbstractArtefactData;
import ru.krt.soap.soapScheme.AbstractSoapScheme;

public class PackageInvokerWrap {

    PackageInvoker packageInvoker = null;

    public Object artefactDataReturn(String packageName, String prefix, String objectId, Object... argument) {
        Object result = null;
        packageInvoker
                .enumArtefactData( packageName, new StringBuilder(),
                        new Reflections(prefix)
                                .getSubTypesOf(AbstractArtefactData.class) );
        result = packageInvoker.invokeMain(packageName, objectId, argument);
        return result;
    }

    public Object soapSchemeReturn(String packageName, String prefix, String objectId) {
        Object result = null;
        packageInvoker = new PackageInvoker();
        packageInvoker
                .enumSoapScheme( packageName, new StringBuilder(),
                        new Reflections(prefix)
                                .getSubTypesOf(AbstractSoapScheme.class) );
        result = packageInvoker.invokeMain(packageName, objectId);
        return result;
    }

}
