package ru.krt.packageInvoker;

import ru.krt.packageInvoker.type.IPackageEnumerator;
import ru.krt.soap.saxhandler.AbstractPackageEnumeratorAndSaxHandler;

public abstract class AbstractPackageEnumerator extends AbstractPackageEnumeratorAndSaxHandler implements IPackageEnumerator {

    protected String objectId;

}
