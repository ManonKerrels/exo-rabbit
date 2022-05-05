package be.technifutur.client.services;

import be.technifutur.client.model.Reservation;

import java.util.List;
import java.util.UUID;

public interface ReservationService {

    Reservation create(Reservation map);

    Reservation setToFacture(UUID ref);

    List<Reservation> getReservFactures();

    Reservation getReservByRef(UUID ref);
}