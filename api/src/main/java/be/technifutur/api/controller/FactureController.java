package be.technifutur.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/fact")
public class FactureController {

    private final RestTemplate template;
    private final String baseURL = "http://localhost:8282/fact";

    public FactureController(RestTemplate template) {
        this.template = template;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<?> getFactures(){
        return template.getForEntity(this.baseURL, Object.class);
    }
}
