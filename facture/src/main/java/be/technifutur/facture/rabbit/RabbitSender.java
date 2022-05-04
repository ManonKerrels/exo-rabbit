package be.technifutur.facture.rabbit;

import be.technifutur.facture.model.Facture;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RabbitSender implements InitializingBean {

    private final RabbitTemplate rabbitTemplate;

    public RabbitSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
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
