package org.bdd.tools.util;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

public class RestApiUtil {

    public RequestSpecification geRequestSpecBasic(String baseuri) {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri(baseuri)
                .setContentType(ContentType.JSON)
                .build();
        return requestSpecification;
    }
    public RequestSpecification getRequestSpecWithQueryParams(String baseuri, HashMap<String,String> queryParameters) {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri(baseuri)
                .setContentType(ContentType.JSON)
                .addQueryParams(queryParameters)
                .build();
        return requestSpecification;
    }
    public RequestSpecification getRequestSpecWithHeaders(String baseuri,HashMap<String, String> headerParameters) {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri(baseuri)
                .setContentType(ContentType.JSON)
                .addHeaders(headerParameters)
                .build();
        return requestSpecification;
    }
    public RequestSpecification getRequestSpecWithHeadersAndCookies(String baseuri,HashMap<String, String> headerParameters,HashMap<String, String> cookieParameters) {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri(baseuri)
                .setContentType(ContentType.JSON)
                .addHeaders(headerParameters)
                .addCookies(cookieParameters)
                .build();
        return requestSpecification;
    }
}
