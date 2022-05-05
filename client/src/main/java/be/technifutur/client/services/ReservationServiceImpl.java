package be.technifutur.client.services;

import be.technifutur.client.model.Reservation;
import be.technifutur.client.rabbit.RabbitSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService{

    List<Reservation> list = new ArrayList<>();
    private static ReservationServiceImpl _instance;
    private final RabbitSender rabbitSender;

    public ReservationServiceImpl(RabbitSender rabbitSender) {
        this.rabbitSender = rabbitSender;
    }

//    public static ReservationServiceImpl getInstance()
//    {
//        if(_instance == null){
//            _instance = new ReservationServiceImpl(new RabbitSender(new RabbitTemplate(), mapper));
//        }
//        return _instance;
//    }

    @Override
    public Reservation create(Reservation map) {
        Reservation r = new Reservation();
        list.add(r);
        rabbitSender.sendReservationToFacture(r.toString());
        return r;
    }

    @Override
    public Reservation setToFacture(UUID ref) {
        return list.stream().filter((r) -> r.getReference() == ref)
                .findFirst().orElseThrow();
    }

    @Override
    public List<Reservation> getReservFactures() {
        return list.stream().filter((r) -> r.getStatus() == Reservation.Status.FACTURE).toList();
    }

    @Override
    public Reservation getReservByRef(UUID ref) {
        return list.stream()
                .filter(r -> r.getReference().equals(ref))
                .findFirst()
                .orElseThrow();
    }
}
