package be.technifutur.facture.rabbit;

import be.technifutur.facture.model.Reservation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {

    private final Logger logger = LoggerFactory.getLogger(RabbitReceiver.class);

    @RabbitListener(queues = "reserv_queue")
    public void listen(String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Reservation r = mapper.readValue(message, Reservation.class);
        logger.info("RESERVATION RECEIVED - " + r);
    }

}
