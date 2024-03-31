package com.example.onlinefoodstore.commons.constans;

import org.springframework.http.HttpMethod;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.List.of;

public interface CommonOpenEndpoints {
    String SIGN_IN = "/api/v1/auth/sign-in";
    String SIGN_UP = "/api/v1/auth/sign-up";
    List<String> SWAGGER_PATHS = List.of(
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/configuration/ui",
            "/swagger-resources",
            "/configuration/security",
            "/swagger-ui.html"
    );

    Map<HttpMethod, List<String>> OPEN_ENDPOINTS = Map.of(
            // Get Methods
            HttpMethod.GET, SWAGGER_PATHS,

            // Post Methods
            HttpMethod.POST, of(
                    SIGN_IN,
                    SIGN_UP
            ),

            // Put Methods
            HttpMethod.PUT, of(
            ),

            // Delete Methods
            HttpMethod.DELETE, of(
            )
    );

    default String[] getGetOpenEndpoints() {
        return getArrayByMethod(HttpMethod.GET);
    }

    default String[] getPostOpenEndpoints() {
        return getArrayByMethod(HttpMethod.POST);
    }

    default String[] getPutOpenEndpoints() {
        return getArrayByMethod(HttpMethod.PUT);
    }

    default String[] getDeleteOpenEndpoints() {
        return getArrayByMethod(HttpMethod.DELETE);
    }

    default String[] getArrayByMethod(HttpMethod method) {
        return getByMethod(method).toArray(new String[]{});
    }

    default List<String> getByMethod(HttpMethod method) {
        var endpoints = OPEN_ENDPOINTS.get(method);
        return Objects.nonNull(endpoints) ? endpoints : Collections.emptyList();
    }
}
