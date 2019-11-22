package graeme.hosford.rob.morgan.assignment02restconsumer.controllers;

import graeme.hosford.rob.morgan.assignment02restconsumer.controllers.forms.BidForm;
import graeme.hosford.rob.morgan.assignment02restconsumer.entities.UserBid;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BidsController {

    @PostMapping("/viewbids")
    public String viewBids(@Valid BidForm bidForm, Model model) {
        RestTemplate rest = new RestTemplate();

        ParameterizedTypeReference<List<UserBid>> userbids = new ParameterizedTypeReference<List<UserBid>>() {
        };

        ResponseEntity<List<UserBid>> bidsResponse =
                rest.exchange("http://localhost:8080/api/userbid/{id}",
                        HttpMethod.GET, new HttpEntity<>(Authorisation.getAuthHeaders()), userbids, bidForm.getUserId());

        List<UserBid> bids = bidsResponse.getBody();

        model.addAttribute(bids);

        return "userbids";
    }

}
