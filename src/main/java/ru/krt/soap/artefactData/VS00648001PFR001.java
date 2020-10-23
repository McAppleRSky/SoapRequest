package ru.krt.soap.artefactData;

public class VS00648001PFR001 extends ArtefactData {
    public VS00648001PFR001() {
        namespace_uri = "http://kvs.pfr.com/snils-by-additionalData/1.0.1";
        id = "VS00648001PFR001";
        addArtefactData(id, namespace_uri);
        //result = new String[1];
    }

    @Override
    public String[] returnRequest(//int... operands
                                        ) {
        System.out.println();
        return new String[0];
    }
}
