package graeme.hosford.rob.morgan.assignment02restconsumer.controllers;

import graeme.hosford.rob.morgan.assignment02restconsumer.entities.Job;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class MainController {

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        RestTemplate rest = new RestTemplate();
        ParameterizedTypeReference<List<Job>> activeJobs = new ParameterizedTypeReference<List<Job>>() {
        };

        String apiUsername = "apiuser@mycit.ie";
        String apiPassword = "password";

        ResponseEntity<List<Job>> response = rest.exchange("http://localhost:8080/api/activejobs",
                HttpMethod.GET, new HttpEntity<>(createHeaders(apiUsername, apiPassword)), activeJobs);

        List<Job> jobs = response.getBody();
        model.addAttribute(jobs);
        return "index";
    }

    private HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] authBytes = auth.getBytes(StandardCharsets.UTF_8);
            byte[] encodedBytes = Base64.encodeBase64(authBytes);
            String authHeader = "Basic " + new String(encodedBytes);
            set(HttpHeaders.AUTHORIZATION, authHeader);
        }};
    }

}
