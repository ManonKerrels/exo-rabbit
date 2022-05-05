package be.technifutur.facture.controller;

import be.technifutur.facture.model.Facture;
import be.technifutur.facture.services.FactureService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/fact")
public class FactureController {

    private final FactureService factureService;

    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }

    @GetMapping("")
    public List<Facture> getFactures(){
        return this.factureService.getFactures();
    }
}
