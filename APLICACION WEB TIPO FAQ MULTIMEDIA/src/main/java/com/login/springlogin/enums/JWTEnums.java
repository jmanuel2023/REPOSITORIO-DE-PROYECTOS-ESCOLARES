package com.login.springlogin.enums;

public enum JWTEnums {
    
    PREFIX_TOKEN("Bearer "),
    CREATE_AUTHORIZATION("Authorization");

    private final String value;

    JWTEnums(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
