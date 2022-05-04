package be.technifutur.client.services;

import be.technifutur.client.model.Reservation;

import java.util.List;
import java.util.UUID;

public interface ReservationService {

    Reservation create();

    Reservation setToFacture(UUID ref);

    List<Reservation> getReservFactures();
}