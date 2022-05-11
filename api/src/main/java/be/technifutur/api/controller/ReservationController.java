package be.technifutur.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/reserv")
public class ReservationController {

    private final RestTemplate restTemplate;
    private final String baseURL = "http://localhost:8181/reserv";

    public ReservationController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //réserver
//    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<?> askForReserv(@RequestBody Map<String, String> request){
        System.out.println(request);
        ResponseEntity<Object> response = restTemplate.postForEntity(this.baseURL, request, Object.class);
        return response;
    }

    //liste des réservations facturées
//    @PreAuthorize("isAuthenticated()")
    @GetMapping("/fact")
    public ResponseEntity<?> getReservFact() {
        return restTemplate.getForEntity(this.baseURL + "/fact", Object.class);
    }

    //ref selon un ID
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<?> getReservByRef(@RequestParam(name = "ref") UUID ref){
        return restTemplate.getForEntity(this.baseURL + "/booking?ref=" + ref, Object.class);
    }

}
