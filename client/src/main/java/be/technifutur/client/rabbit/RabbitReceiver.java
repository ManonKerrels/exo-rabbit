package be.technifutur.client.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {

    @RabbitListener(queues = "fact_queue") //quelle queue est-on en train d'écouter
    public void listen(Message message){
        System.out.println(message);
    }
}
