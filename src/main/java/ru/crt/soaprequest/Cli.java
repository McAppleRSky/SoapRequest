package ru.crt.soaprequest;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Cli implements namespaces{

    private static final Logger logger;
    static {
        System.setProperty("java.util.logging.SimpleFormatter.format", "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        logger = Logger.getLogger(Cli.class.getName());
        logger.setLevel(Level.WARNING);
    }

    public static void main(String[] args) {
        if (args.length==0) {
            args = new String[1];
            args[0] = VS00648001PFR001;
        }


    }
}
