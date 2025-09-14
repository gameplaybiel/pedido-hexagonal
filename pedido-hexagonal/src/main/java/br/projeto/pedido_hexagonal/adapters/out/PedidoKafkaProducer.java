package br.projeto.pedido_hexagonal.adapters.out;

import br.projeto.pedido_hexagonal.adapters.in.dto.PedidoDTO;
import br.projeto.pedido_hexagonal.adapters.in.mapper.PedidoMapper;
import br.projeto.pedido_hexagonal.domain.entity.Pedido;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class PedidoKafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final String topic = "pedidos-request"; // pode externalizar no application.yml

    public PedidoKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarPedido(Pedido pedido) {
        // aqui você pode usar JSON para serializar
        PedidoDTO dto = PedidoMapper.toDTO(pedido);
        String mensagem = String.format("Pedido{id=%d, descricao='%s', valor=%.2f, dataCriacao=%s}",
                dto.getId(), dto.getDescricao(), dto.getValor(), dto.getDataCriacao());

        kafkaTemplate.send(topic, mensagem);
    }
}
