package be.technifutur.facture.rabbit;

import be.technifutur.facture.model.Reservation;
import be.technifutur.facture.services.FactureService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

@Component
public class RabbitEcouteur {

    private final Logger logger = LoggerFactory.getLogger(RabbitEcouteur.class);
    private final FactureService service;
    private final ObjectMapper mapper;

    public RabbitEcouteur(FactureService service, ObjectMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

//    @RabbitListener(queues = "reserv_queue")
//    public void receiveReservation(String message) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        Reservation r = mapper.readValue(message, Reservation.class);
//        logger.info("RESERVATION RECEIVED - "+ r);
//        this.service.createFacture(
//                (int) ChronoUnit.DAYS.between((Temporal) r.getArrive(), (Temporal) r.getDepart())*20,
//                r.getReference()
//                );
//    }

    @RabbitListener(queues = "reserv_queue")
    public void receiveReservation(String message) throws JsonProcessingException {
        Reservation r = mapper.readValue(message, Reservation.class);
        logger.info("BOOKING RECEIVED - " + r);
        this.service.createFacture(
                (int)ChronoUnit.DAYS.between(r.getArrive(), r.getDepart())*10,
                r.getReference()
                );
    }
}
