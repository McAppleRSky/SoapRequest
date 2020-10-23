package ru.krt.soap;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cli{

    private static final Logger logger;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        logger = Logger.getLogger(Cli.class.getName());
        logger.setLevel(Level.WARNING);
    }

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

    public static void main(String[] args) {
        RequestGenerator requestGenerator = null;
        try {
            requestGenerator = new RequestGenerator();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        if (args.length==0) {
            args = new String[1];
            args[0] = "http://kvs.pfr.com/snils-by-additionalData/1.0.1";
        }
        requestGenerator.setArtefactData(args[0]);
        toFile("request.xml", requestGenerator.generate());
    }


}
