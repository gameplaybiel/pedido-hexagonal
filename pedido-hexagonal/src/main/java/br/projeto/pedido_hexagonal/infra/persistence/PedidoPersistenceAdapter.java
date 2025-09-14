package br.projeto.pedido_hexagonal.infra.persistence;

import br.projeto.pedido_hexagonal.domain.entity.Pedido;
import br.projeto.pedido_hexagonal.domain.repository.PedidoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PedidoPersistenceAdapter {
    private final PedidoRepository repository;

    public PedidoPersistenceAdapter(PedidoRepository repository) {
        this.repository = repository;
    }

    public Pedido salvar(Pedido pedido) {
        return repository.save(pedido);
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public List<Pedido> listarTodos() {
        return repository.findAll();
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
