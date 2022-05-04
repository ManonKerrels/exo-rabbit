package be.technifutur.client.services;

import be.technifutur.client.model.Reservation;
import be.technifutur.client.rabbit.RabbitSender;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReservationServiceImpl implements ReservationService{

    List<Reservation> list = new ArrayList<>();
    private static ReservationServiceImpl _instance;
    private final RabbitSender rabbitSender;

    public ReservationServiceImpl(RabbitSender rabbitSender) {
        this.rabbitSender = rabbitSender;
    }

    public static ReservationServiceImpl getInstance()
    {
        if(_instance == null){
            _instance = new ReservationServiceImpl(new RabbitSender(new RabbitTemplate()));
        }
        return _instance;
    }

    @Override
    public Reservation create() {
        Reservation r = new Reservation();
        list.add(r);
        rabbitSender.sendReservationToFacture(r.toString());
        return r;
    }

    @Override
    public Reservation setToFacture(UUID ref) {
//        list.stream()
//                .anyMatch((r) -> r.getReference() == ref);
        return list.stream().filter((r) -> r.getReference() == ref)
                .findFirst().orElseThrow();
    }

    @Override
    public List<Reservation> getReservFactures() {
        return list.stream().filter((r) -> r.getStatus() == Reservation.Status.FACTURE).toList();
    }
}
