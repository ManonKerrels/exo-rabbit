package be.technifutur.client.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    //permet de gérer les échanges, de faire la connection
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

//    //permet de configurer les dates pour utiliser le LocalDate (et pas juste Date)
//    @Bean
//    public ObjectMapper objectMapper(){
//        return new ObjectMapper().registerModule(new JavaTimeModule());
//    }

    //queue sur la réservation
    @Bean("reserv_queue") //par défaut, son nom de Bean est le nom de la méthode
    public Queue reservQueue(){
        return new Queue("reserv_queue", false);
    }

    //queue sur la facture
    @Bean("fact_queue")
    public Queue factQueue(){
        return new Queue("fact_queue", false);
    }

    //permet l'échange
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("topic.reserv", false, false);
    }

    //permet le binding (lien) avec la réservation (on reprécise de qui il s'agit avec le "with")
    @Bean
    public Binding binding(DirectExchange exchange, Queue reserv_queue){ //réutilisation du nom du Bean
        return BindingBuilder.bind(reserv_queue).to(exchange).with("reserv");
    }
}
