package org.example.resapijava.entity.enums;

public enum Role {
    USER("user"),
    ADMIN("admin"),
    MODERATOR("moderator");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Role{" +
                "value='" + value + '\'' +
                '}';
    }
}
