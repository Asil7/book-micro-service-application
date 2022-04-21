package uz.pdp.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @GetMapping("/fall-back-uri")
    public String getFallBackUri(){
        return "Server Down, Please try again some time later.";
    }

}
