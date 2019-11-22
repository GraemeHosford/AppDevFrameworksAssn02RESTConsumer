package graeme.hosford.rob.morgan.assignment02restconsumer.controllers;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

import java.nio.charset.StandardCharsets;

class Authorisation {

    private static HttpHeaders authHeaders;

    private Authorisation() {
    }

    static HttpHeaders getAuthHeaders() {
        if (authHeaders != null) {
            return authHeaders;
        }

        authHeaders = new HttpHeaders() {{
            String auth = "apiuser@mycit.ie" + ":" + "password";
            byte[] authBytes = auth.getBytes(StandardCharsets.UTF_8);
            byte[] encodedBytes = Base64.encodeBase64(authBytes);
            String authHeader = "Basic " + new String(encodedBytes);
            set(HttpHeaders.AUTHORIZATION, authHeader);
        }};

        return authHeaders;
    }

}
