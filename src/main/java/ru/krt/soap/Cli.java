package ru.krt.soap;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Cli{

    // https://www.ibm.com/developerworks/ru/library/x-jaxmsoap/
    // https://turreta.com/2016/11/11/java-compare-xml-files-using-xmlunit/

    private static final Logger logger;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        logger = Logger.getLogger(Cli.class.getName());
        logger.setLevel(Level.WARNING);
    }

/*
    private static String toString(ByteArrayOutputStream byteArrayOutputStream){
        String result = null;
        try {
            result = new String(byteArrayOutputStream.toByteArray(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void toFile(String pathname, ByteArrayOutputStream byteArrayOutputStream) {
        File file = new File(pathname);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            byteArrayOutputStream.writeTo(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
*/

    public static void main(String[] args) {
/*
        RequestGenerator requestGenerator = null;
        try {
            requestGenerator = new RequestGenerator();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
*/
        if (args.length==0) {
            args = new String[2];
            args[0] = "http://smev3-n0.test.gosuslugi.ru:7500/smev/v1.1/ws?wsdl";
            args[1] = "http://kvs.pfr.com/snils-by-additionalData/1.0.1";
        }
        String wsdlId = args[0], wsdlPrefix = "ru.krt.soap.soapScheme", wsdlPackage = "soapScheme",
                artefactId = args[1],
                artefactPackage = "artefactData",
                artefactPrefix = "ru.krt.soap.artefactData";

        //byte[] wsdlBytes = null;
        PackageInvokerWrap packageInvokerWrap = new PackageInvokerWrap();
        Object wsdlTemplate = packageInvokerWrap.soapSchemeReturn(
                wsdlPackage,
                wsdlPrefix,
                wsdlId
        );
        Object requestDocument = packageInvokerWrap.artefactDataReturn(
                artefactPackage,
                artefactPrefix,
                artefactId,
                wsdlTemplate
        );


/*
        PackageInvoker
            soapScheme
                = new PackageInvoker(//"ru.krt.soap.soapScheme",
                                        //AbstractSoapScheme.class
        );
        soapScheme.invokeMain("",args[0]);
*/
        //        PlainObject wsdlRequestTemplate
//            = (PlainObject)
/*
        (ByteArrayOutputStream) forReflectArtefact.getMethod().invoke(forReflectArtefact.getInstance(), param);

        soapScheme.listObject.get("")

                packageInvoker
*/
/*
        requestGenerator.setArtefactData(args[0]);
        toFile("request.xml", requestGenerator.generate());
*/
    }

}
