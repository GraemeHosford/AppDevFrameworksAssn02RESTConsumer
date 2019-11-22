package graeme.hosford.rob.morgan.assignment02restconsumer.controllers;

import graeme.hosford.rob.morgan.assignment02restconsumer.entities.Job;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class JobsController {

    @GetMapping("activejobs")
    public String activeJobs(Model model) {
        RestTemplate rest = new RestTemplate();
        ParameterizedTypeReference<List<Job>> activeJobs = new ParameterizedTypeReference<List<Job>>() {
        };

        ResponseEntity<List<Job>> jobsResponse = rest.exchange("http://localhost:8080/api/activejobs",
                HttpMethod.GET, new HttpEntity<>(Authorisation.getAuthHeaders()), activeJobs);

        List<Job> jobs = jobsResponse.getBody();

        model.addAttribute(jobs);
        return "activejobs";
    }

}
