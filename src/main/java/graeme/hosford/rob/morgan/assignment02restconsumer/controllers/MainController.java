package graeme.hosford.rob.morgan.assignment02restconsumer.controllers;

import graeme.hosford.rob.morgan.assignment02restconsumer.entities.Job;
import graeme.hosford.rob.morgan.assignment02restconsumer.entities.UserBid;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class MainController {

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        RestTemplate rest = new RestTemplate();
        ParameterizedTypeReference<List<Job>> activeJobs = new ParameterizedTypeReference<List<Job>>() {
        };

        ResponseEntity<List<Job>> jobsResponse = rest.exchange("http://localhost:8080/api/activejobs",
                HttpMethod.GET, new HttpEntity<>(createHeaders()), activeJobs);

        List<Job> jobs = jobsResponse.getBody();

        model.addAttribute(jobs);
        model.addAttribute(new BidForm());

        return "index";
    }

    @PostMapping("/viewbids")
    public String viewBids(@Valid BidForm bidForm, Model model) {
        RestTemplate rest = new RestTemplate();

        ParameterizedTypeReference<List<UserBid>> userbids = new ParameterizedTypeReference<List<UserBid>>() {
        };

        ResponseEntity<List<UserBid>> bidsResponse =
                rest.exchange("http://localhost:8080/api/userbid/{id}",
                        HttpMethod.GET, new HttpEntity<>(createHeaders()), userbids, bidForm.getUserId());

        List<UserBid> bids = bidsResponse.getBody();

        model.addAttribute(bids);

        return "userbids";
    }

    private HttpHeaders createHeaders() {
        return new HttpHeaders() {{
            String auth = "apiuser@mycit.ie" + ":" + "password";
            byte[] authBytes = auth.getBytes(StandardCharsets.UTF_8);
            byte[] encodedBytes = Base64.encodeBase64(authBytes);
            String authHeader = "Basic " + new String(encodedBytes);
            set(HttpHeaders.AUTHORIZATION, authHeader);
        }};
    }

}
