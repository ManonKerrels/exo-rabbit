package be.technifutur.facture.config;

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

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean("reserv_queue")
    public Queue reservQueue(){
        return new Queue("reserv_queue", false);
    }

    @Bean("fact_queue")
    public Queue factQueue(){
        return new Queue("fact_queue", false);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("topic.reserv", false, false);
    }

    @Bean
    public Binding binding(DirectExchange exchange, Queue fact_queue){
        return BindingBuilder.bind(fact_queue).to(exchange).with("fact");
    }

}
