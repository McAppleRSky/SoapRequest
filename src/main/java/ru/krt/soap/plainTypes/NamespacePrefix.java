package ru.krt.soap.plainTypes;

public class NamespacePrefix {
    private String prefix, namespace;

    public NamespacePrefix(String prefix//, String namespace
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
