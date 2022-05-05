package be.technifutur.facture.rabbit;

import be.technifutur.facture.model.Facture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

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

    public void sendFactureToClient(String json){
        rabbitTemplate.convertAndSend("topic.reserv", "fact", json);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Facture f = new Facture(
                50,
                UUID.randomUUID()
        );

        ObjectMapper mapper = new ObjectMapper();
        String fJson = mapper.writeValueAsString(f);
        sendFactureToClient(fJson);
    }
}
