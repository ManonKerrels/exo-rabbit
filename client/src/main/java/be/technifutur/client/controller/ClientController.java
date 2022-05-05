package be.technifutur.client.controller;

import be.technifutur.client.form.ReservationForm;
import be.technifutur.client.model.Reservation;
import be.technifutur.client.services.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reserv")
public class ClientController {

    private final ReservationService reservationService;

    public ClientController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/fact")
    public List<Reservation> getReservations(){
        return this.reservationService.getReservFactures();
    }

    @GetMapping("/one")
    public Reservation getReservByRef(@RequestParam(name = "ref") UUID ref){
        return this.reservationService.getReservByRef(ref);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createReserv(@RequestBody ReservationForm form) {
        this.reservationService.create(form.map());
    }{

    }

}
