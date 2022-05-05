package be.technifutur.client.rabbit;

import be.technifutur.client.model.Facture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitEcouteur {

    private final Logger logger = LoggerFactory.getLogger(RabbitEcouteur.class);
    private final ObjectMapper mapper;

    public RabbitEcouteur(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @RabbitListener(queues = "fact_queue")
    public void receiveFacture(String message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Facture f = mapper.readValue(message, Facture.class);
        logger.info("FACTURE RECEIVED - " + f);
    }

}
