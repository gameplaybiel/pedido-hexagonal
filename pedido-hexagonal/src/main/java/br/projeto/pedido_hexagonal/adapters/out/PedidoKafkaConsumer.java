package br.projeto.pedido_hexagonal.adapters.out;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoKafkaConsumer {

    @KafkaListener(topics = "pedidos-request", groupId = "pedido-group")
    public void consumir(String message){
        System.out.println("Mensagem recebida de Kafka: " + message);
    }
}
