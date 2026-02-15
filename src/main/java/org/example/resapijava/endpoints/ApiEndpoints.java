package org.example.resapijava.endpoints;

public class ApiEndpoints {
    private ApiEndpoints(){}

    public static final String USERS = "/";
    public static final String USER_ID = "/{id}";
    public static final String USER_CREATE = "/create/user";
    public static final String USER_CHANGE = "/change/{id}";

    public static final String API_V1 = "/api/v1";
}
