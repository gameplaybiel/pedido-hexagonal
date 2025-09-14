package br.projeto.pedido_hexagonal.infra.kafka;

import br.projeto.pedido_hexagonal.adapters.in.dto.PedidoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PedidoRequestProducer {

    @Value("${topicos.pedidos.request.topic}")
    private String topicoPedidosRequest;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public String sendMessage(PedidoDTO pedido) throws JsonProcessingException {
        String orderAsMessage = objectMapper.writeValueAsString(pedido);
        kafkaTemplate.send(topicoPedidosRequest, orderAsMessage);
        return "Pedido enviado!";
    }
}
