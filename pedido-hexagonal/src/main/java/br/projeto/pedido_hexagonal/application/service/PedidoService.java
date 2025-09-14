package br.projeto.pedido_hexagonal.application.service;

import br.projeto.pedido_hexagonal.adapters.out.PedidoKafkaProducer;
import br.projeto.pedido_hexagonal.application.usecase.PedidoUseCase;
import br.projeto.pedido_hexagonal.domain.entity.Pedido;
import br.projeto.pedido_hexagonal.infra.persistence.PedidoPersistenceAdapter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService implements PedidoUseCase {
    private final PedidoPersistenceAdapter persistenceAdapter;
    private final PedidoKafkaProducer producer;

    public PedidoService(PedidoPersistenceAdapter persistenceAdapter, PedidoKafkaProducer producer) {
        this.persistenceAdapter = persistenceAdapter;
        this.producer = producer;
    }

    @Override
    public Pedido criarPedido(Pedido pedido) {
        pedido.setDataCriacao(LocalDate.now());
        Pedido salvo = persistenceAdapter.salvar(pedido);

        //Enviar para Kafka
        producer.enviarPedido(salvo);
        return salvo;
    }

    @Override
    public Optional<Pedido> buscaPedidoPorId(Long id) {
        return persistenceAdapter.buscarPorId(id);
    }

    @Override
    public List<Pedido> listarPedido() {
        return persistenceAdapter.listarTodos();
    }

    @Override
    public void deletarPedido(Long id) {
        persistenceAdapter.deletar(id);
    }
}
