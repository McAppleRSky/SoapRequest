package ru.krt.soap;

public class PlainNamespacePrefix {
    private String prefix, namespace;

    public PlainNamespacePrefix(String prefix//, String namespace
                                            ) {
//        this.namespace = namespace;
        this.prefix = prefix;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
