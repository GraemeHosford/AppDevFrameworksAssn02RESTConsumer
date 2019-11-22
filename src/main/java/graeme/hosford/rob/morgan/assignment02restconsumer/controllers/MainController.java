package graeme.hosford.rob.morgan.assignment02restconsumer.controllers;

import graeme.hosford.rob.morgan.assignment02restconsumer.controllers.forms.BidForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute(new BidForm());
        return "index";
    }

}
