package ru.krt.soap;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Cli{

    private static final Logger logger;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        logger = Logger.getLogger(Cli.class.getName());
        logger.setLevel(Level.WARNING);
    }

    public static void main(String[] args) {
        RequestGenerator requestGenerator = new RequestGenerator();
        if (args.length==0) {
            args = new String[1];
            args[0] = "http://kvs.pfr.com/snils-by-additionalData/1.0.1";
        }
        requestGenerator.setArtefactData(args[0]);


    }
}
