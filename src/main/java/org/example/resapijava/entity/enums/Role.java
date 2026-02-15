package org.example.resapijava.entity.enums;

public enum Role {
    ROLE_USER("user"),
    ROLE_ADMIN("admin"),
    ROLE_MODERATOR("moderator");

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
