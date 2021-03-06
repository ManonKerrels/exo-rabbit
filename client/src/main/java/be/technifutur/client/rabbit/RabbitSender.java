package be.technifutur.client.rabbit;

import be.technifutur.client.model.Reservation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class RabbitSender implements InitializingBean {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;
    private final Logger logger = LoggerFactory.getLogger(RabbitSender.class);

    public RabbitSender(RabbitTemplate rabbitTemplate, ObjectMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
    }

    public void sendReservationToFacture(String json){
        rabbitTemplate.convertAndSend("topic.reserv", "reserv", json);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Reservation r = new Reservation(
                UUID.randomUUID(),
                new Date(),
                new Date(),
                Reservation.Status.DEMANDE
        );

        ObjectMapper mapper = new ObjectMapper();
        String fJson = mapper.writeValueAsString(r);
        sendReservationToFacture(fJson);
    }
}
