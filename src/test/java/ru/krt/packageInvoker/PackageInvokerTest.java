package ru.krt.packageInvoker;

import org.junit.Test;
import ru.krt.soap.artefactData.ArtefactData;
import ru.krt.soap.soapScheme.SoapScheme;

import static org.junit.Assert.*;

public class PackageInvokerTest {

    @Test
    public void testList (){
        PackageInvoker
                packageInvoker = new PackageInvoker("ru.krt.soap.soapScheme", SoapScheme.class)
                ,packageInvoker1 = new PackageInvoker("ru.krt.soap.artefactData", ArtefactData.class);
        assertEquals(2, packageInvoker.listObject.size());
        assertEquals(1, packageInvoker1.listObject.size());
    }

}
