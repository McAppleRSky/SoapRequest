package ru.krt.soap.types.plain;

public class NamespacePrefix {
    private String prefix;
    private String namespace;

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
